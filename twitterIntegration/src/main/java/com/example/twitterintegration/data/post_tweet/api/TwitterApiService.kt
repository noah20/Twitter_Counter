package com.example.twitterintegration.data.post_tweet.api

import com.example.twitterintegration.data.PostTweetRequest
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST


interface TwitterApiService {
    @Headers("Content-Type: application/json")
    @POST("2/tweets")
    suspend fun postTweet(
        @Body tweetRequest: PostTweetRequest?,
        @Header("Authorization") authHeader: String = "AAAAAAAAAAAAAAAAAAAAALDvugEAAAAAB1DAXa%2FIaGUSTsE6VS1R4mhRib0%3Dyk58YlQrKMrzdCtNJSYY9ODEknOrAflkcMNeblGfIoLKXSOM0A"
    )
}

