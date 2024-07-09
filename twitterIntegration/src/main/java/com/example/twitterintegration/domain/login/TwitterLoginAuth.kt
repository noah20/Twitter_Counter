package com.example.twitterintegration.domain.login

import com.example.twitterintegration.data.PostTweetRequest
import com.example.twitterintegration.data.model.LoginResponse

interface TwitterLoginAuth {

    suspend fun login():LoginResponse?
}