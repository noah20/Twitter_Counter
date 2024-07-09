package com.example.twitterintegration.data.post_tweet.repo

import com.example.twitterintegration.data.model.PostTweetRequest
import com.example.twitterintegration.data.post_tweet.api.TwitterApiService
import com.example.twitterintegration.domain.post_tweet.PostTweetRepository

class PostTweetRepositoryImp(private val twitterService: TwitterApiService): PostTweetRepository {

    override suspend fun post(req: PostTweetRequest, token: String) {
        twitterService.postTweet(req,token)
    }
}