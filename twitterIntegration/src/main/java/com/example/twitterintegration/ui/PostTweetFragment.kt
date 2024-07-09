package com.example.twitterintegration.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import com.example.theming.binding.model.CounterDataModel
import com.example.theming.binding.model.ToolBarConfig
import com.example.twitterintegration.R
import com.example.twitterintegration.data.utils.ErrorCodes
import com.example.twitterintegration.data.utils.ServerException
import com.example.twitterintegration.databinding.FragmentPostTweetBinding
import com.example.twitterintegration.utils.common_extensions.copyText
import com.example.twitterintegration.utils.common_extensions.show
import com.example.twitterintegration.utils.common_extensions.showErrorDialog
import com.example.twitterintegration.utils.common_extensions.showToast
import dagger.hilt.android.AndroidEntryPoint

const val MAX_TWEET_LENGTH = 280

@AndroidEntryPoint
class PostTweetFragment : TwitterAuthHandler() {

    private val maxTweetLengthKey = "max-length"

    private val maxTweetLength:Int by lazy {
        arguments?.getInt(maxTweetLengthKey) ?: MAX_TWEET_LENGTH
    }

    companion object {
        fun newInstance(maxLength:Int = MAX_TWEET_LENGTH) = PostTweetFragment().apply {
            this.arguments = bundleOf(maxTweetLengthKey to maxLength)
        }
    }

    private var mBinding: FragmentPostTweetBinding? = null

    private val viewModel: PostTwitViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentPostTweetBinding.inflate(inflater,container,false)
        mBinding?.toolbarConfig = getToolbarConfig()
        mBinding?.typedCounterConfig = getTypedCounter()
        mBinding?.remainingCounterConfig = getRemainingCounter()
        return mBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        handleTweetLength()

        mBinding?.btnPost?.setOnClickListener {
            val tweet = mBinding?.etTweet?.text?.toString() ?: return@setOnClickListener
//            startAuthUser()
            postTweet(tweet)
        }
        mBinding?.btnClear?.setOnClickListener {
            mBinding?.etTweet?.text = null
        }
        mBinding?.btnCopy?.setOnClickListener {
            val tweet = mBinding?.etTweet?.text?.toString()
           context?.copyText(tweet)
        }
    }

    private fun postTweet(tweet: String) {
        if(!viewModel.isTweetValid(tweet,maxTweetLength)){
            context?.showToast(getString(R.string.tweet_limited_by_characters, maxTweetLength))
            return
        }

        mBinding?.progressBar?.show(true)
        viewModel.postTweet(tweet).observe(viewLifecycleOwner){
            mBinding?.progressBar?.show(false)
            if(it.isSuccess){
                context?.showToast(getString(R.string.posted_successfully))
            }else{
                val error = it.exceptionOrNull()
                if(error is ServerException){
                    if(error.status == ErrorCodes.UNAUTHORIZED){
                        startAuthUser()
                        activity?.showErrorDialog(getString(R.string.post_from_twitter) , getString(R.string.post_error, error.title),null)
                    }else{
                        activity?.showErrorDialog(getString(R.string.post_from_twitter) , getString(R.string.post_error, error.title)){
                            openTweeterIntent(tweet)
                        }
                    }
                }else{
                    activity?.showErrorDialog(getString(R.string.post_from_twitter) , getString(R.string.post_error, it.exceptionOrNull()?.message)){
                        openTweeterIntent(tweet)
                    }
                }

            }
        }
    }



    private fun handleTweetLength() {
        mBinding?.etTweet?.addTextChangedListener(TweetTextWatcher {
            mBinding?.remainingCounterConfig = getRemainingCounter(MAX_TWEET_LENGTH - it)
            mBinding?.typedCounterConfig = getTypedCounter(it)
        })
    }

    private fun getRemainingCounter(count: Int? = null): CounterDataModel? {
        return if (count == null) {
            CounterDataModel(
                label = getString(R.string.characters_remaining),
                count = getString(R.string.remaining_counter,  maxTweetLength)
            )
        } else {
            mBinding?.remainingCounterConfig?.copy(
                count = getString(R.string.remaining_counter,  count))
        }
    }

    private fun getTypedCounter(count: Int? = null): CounterDataModel? {
        return if (count == null) {
            CounterDataModel(
                label = getString(R.string.characters_typed),
                count = getString(R.string.typed_counter, 0, maxTweetLength)
            )
        } else {
            mBinding?.typedCounterConfig?.copy(
                count = getString(R.string.typed_counter, count, maxTweetLength))
        }
    }

    private fun getToolbarConfig(): ToolBarConfig {
        return ToolBarConfig(title = getString(R.string.twitter_character_count)){
            activity?.finish()
        }
    }

}