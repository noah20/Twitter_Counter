package com.example.twitterintegration.domain.post_tweet.usecase

import com.example.twitterintegration.data.model.PostTweetRequest
import com.example.twitterintegration.domain.post_tweet.PostTweetRepository
import javax.inject.Inject


class PostTweetUseCase @Inject constructor(private val postTweetRepo: PostTweetRepository) {

    suspend fun execute(req: PostTweetRequest, token: String) {
        postTweetRepo.post(req,token)
    }

}
