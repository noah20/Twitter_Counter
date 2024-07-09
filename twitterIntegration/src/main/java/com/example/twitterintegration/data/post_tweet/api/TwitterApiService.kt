package com.example.twitterintegration.data.post_tweet.api

import com.example.twitterintegration.data.model.PostTweetRequest
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST


interface TwitterApiService {
    @Headers("Content-Type: application/json")
    @POST("2/tweets")
    suspend fun postTweet(
        @Body tweetRequest: PostTweetRequest?,
        @Header("authorization") token: String
    )
}

