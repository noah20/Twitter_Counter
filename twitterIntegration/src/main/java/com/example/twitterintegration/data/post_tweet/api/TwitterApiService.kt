package com.example.twitterintegration.data.post_tweet.api

import com.example.twitterintegration.data.PostTweetRequest
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST


interface TwitterApiService {

    @Headers("Content-Type: application/json")
    @POST("tweets")
    suspend fun postTweet(@Body tweetRequest: PostTweetRequest?)
}

