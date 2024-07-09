package com.example.tweetvalidator.model

object TweetValidatorManager {

    var Config: TweetLengthConfig = TweetLengthConfig()

    private var INSTANCE: TweetValidatorManager

    init {
        synchronized(TweetValidatorManager::class.java) {
            INSTANCE = TweetValidatorManager
        }
    }

    fun update(validationConfig: TweetLengthConfig) {
        Config = validationConfig
    }

}