package com.example.twitterintegration.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.theming.BuildConfig
import com.example.tweetvalidator.tweet_validator.TweetLengthValidator
import com.example.twitterintegration.data.PostTweetRequest
import com.example.twitterintegration.data.utils.ServerException
import com.example.twitterintegration.domain.post_tweet.usecase.PostTweetUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class PostTwitViewModel @Inject constructor(private val service: PostTweetUseCase) : ViewModel() {

    fun postTweet(txt: String) = liveData(Dispatchers.IO) {
        try {
            service.execute(PostTweetRequest(txt))
            emit(Result.success(true))
        } catch (e: Exception) {
            if(BuildConfig.DEBUG){
                e.printStackTrace()
            }
            if(e is ServerException){
                emit(Result.failure(Exception(e.title)))
            }else
                emit(Result.failure(e))
        }
    }

    fun isTweetValid(tweet: String, maxTweetLength: Int): Boolean {
        val actualLength = TweetLengthValidator.calculateTwitterLength(tweet)
        return (maxTweetLength - actualLength) >= 0
    }
}