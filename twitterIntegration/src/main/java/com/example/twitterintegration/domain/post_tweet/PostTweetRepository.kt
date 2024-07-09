package com.example.twitterintegration.domain.post_tweet

import com.example.twitterintegration.data.model.PostTweetRequest


interface PostTweetRepository {

    suspend fun post(req: PostTweetRequest, token: String)

}