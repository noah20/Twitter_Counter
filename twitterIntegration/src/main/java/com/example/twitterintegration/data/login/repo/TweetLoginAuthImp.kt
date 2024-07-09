package com.example.twitterintegration.data.login.repo

import com.example.twitterintegration.data.PostTweetRequest
import com.example.twitterintegration.data.login.api.TwitterLoginApiService
import com.example.twitterintegration.data.model.LoginResponse
import com.example.twitterintegration.data.post_tweet.api.TwitterApiService
import com.example.twitterintegration.domain.login.TwitterLoginAuth
import com.example.twitterintegration.domain.post_tweet.PostTweetRepository

class TweetLoginAuthImp(private val api: TwitterLoginApiService): TwitterLoginAuth {

    override suspend fun login(): LoginResponse? {
        return api.login()
    }


}