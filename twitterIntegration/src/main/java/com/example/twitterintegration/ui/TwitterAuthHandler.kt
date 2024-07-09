package com.example.twitterintegration.ui

import android.content.Intent
import android.net.Uri
import androidx.fragment.app.Fragment

private const val POST_TWEET_URL  = "https://twitter.com/intent/tweet?text="

open class TwitterAuthHandler:Fragment() {
    fun openTweeterIntent(tweet: String) {
        val tweetUrl = (POST_TWEET_URL.plus(tweet))
        val uri = Uri.parse(tweetUrl)
        startActivity(Intent(Intent.ACTION_VIEW, uri))
    }
}