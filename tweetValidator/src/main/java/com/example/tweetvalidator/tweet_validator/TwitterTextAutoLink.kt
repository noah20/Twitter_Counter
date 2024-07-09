package com.example.tweetvalidator.tweet_validator

import com.example.tweetvalidator.tweet_validator.Regex.VALID_URL_PATTERN_STRING
import java.util.regex.Pattern

internal object TwitterTextAutoLink {

    var VALID_LINK_PATTERN:Pattern

    init {
        synchronized(TwitterTextEmojiRegex::class.java) {
            VALID_LINK_PATTERN = Pattern.compile(VALID_URL_PATTERN_STRING, Pattern.CASE_INSENSITIVE)
        }
    }

}
