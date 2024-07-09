package com.example.twitterintegration.ui

import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.twitterintegration.data.utils.ApiKeys
import com.example.twitterintegration.data.utils.TwitterClientAuthGenerator


open class TwitterAuthHandler:Fragment() {

    private var codeVerifier: String = ""

    private val viewModel: PostTwitViewModel by viewModels()

    fun startAuthUser(){
        codeVerifier = TwitterClientAuthGenerator.generateCodeVerifier()
        val state: String = TwitterClientAuthGenerator.generateRandomState()
        val codeChallenge: String = TwitterClientAuthGenerator.generateCodeChallenge(codeVerifier)
        val responseType = "code"
        viewModel.saveGeneratedCode(codeVerifier)
        val authorizationUrl = ApiKeys.buildClientAuth(
            responseType = responseType,
            clientId = ApiKeys.clientId,
            redirectUrl = ApiKeys.redirectUrl,
            state = state,
            codeChallenge = codeChallenge
        )
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(authorizationUrl))

        activity?.startActivity(intent)
    }

    override fun onResume() {
        super.onResume()
        Log.d("TAG", "onResume: noah here new data ${activity?.intent?.data}")
        activity?.intent?.let {
            handleIntent(it)
        }
    }

    fun openTweeterIntent(tweet: String) {
        val tweetUrl = (ApiKeys.POST_TWEET_URL.plus(tweet))
        val uri = Uri.parse(tweetUrl)
        startActivity(Intent(Intent.ACTION_VIEW, uri))
    }

    private fun handleIntent(intent: Intent) {
        val uri = intent.data
        if (uri != null && uri.toString().startsWith(ApiKeys.redirectUrl)) {
            val code = uri.getQueryParameter("code")
            if (code != null) {
                exchangeCodeForAccessToken(code)
            }
        }
    }

    private fun exchangeCodeForAccessToken(code: String) {
        viewModel.doClientAuth(code , viewModel.getGeneratedCode() ?: "").observe(viewLifecycleOwner){

        }
    }


}