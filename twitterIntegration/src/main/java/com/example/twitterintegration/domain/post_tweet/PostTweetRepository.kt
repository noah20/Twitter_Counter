package com.example.twitterintegration.domain.post_tweet

import com.example.twitterintegration.data.PostTweetRequest


interface PostTweetRepository {

    suspend fun post(req: PostTweetRequest)

}