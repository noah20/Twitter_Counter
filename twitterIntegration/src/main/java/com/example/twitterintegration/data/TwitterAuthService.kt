package com.example.twitterintegration.data

import com.github.scribejava.apis.TwitterApi
import com.github.scribejava.core.builder.ServiceBuilder
import com.github.scribejava.core.model.OAuth2AccessToken
import com.github.scribejava.core.oauth.OAuth10aService


class TwitterAuthService {

    val service: OAuth10aService = ServiceBuilder(API_KEY)
        .apiSecret(API_SECRET_KEY)
        .callback(CALLBACK_URL)
        .build(TwitterApi.instance())

    var accessToken: OAuth2AccessToken? = null

    companion object {
         const val API_KEY = "myzCsYas08779O5mxmHPt1NfD"
         const val API_SECRET_KEY = "jhigOCsmTQUdEJ1mJqwXqonDVgKOkoTJteEGz1RQm03KPZK840"
         const val CALLBACK_URL = "myapp://callback"
    }
}
