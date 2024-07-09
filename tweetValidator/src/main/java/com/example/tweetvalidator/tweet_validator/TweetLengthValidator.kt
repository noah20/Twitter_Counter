package com.example.tweetvalidator.tweet_validator

import com.example.tweetvalidator.model.TweetValidatorManager

class TweetLengthValidator {

    companion object {

        fun calculateTwitterLength(text: String): Int {

            val emojiMatcher = TwitterTextEmojiRegex.VALID_EMOJI_PATTERN.matcher(text)
            val linkMatcher = TwitterTextAutoLink.VALID_LINK_PATTERN.matcher(text)
//        val linkMatcher = Patterns.WEB_URL.matcher(text)

            val emojiWeight = TweetValidatorManager.Config.emojiCount
            val linkWeight = TweetValidatorManager.Config.linkCount

            val emojiList = ArrayList<String>()
            val linkList = ArrayList<String>()
            var copyText = text
            while (emojiMatcher.find()) {
                emojiList.add(emojiMatcher.group())
            }
            while (linkMatcher.find()) {
                linkList.add(linkMatcher.group())
            }
            emojiList.forEach {
                copyText = copyText.replace(it, "")
            }
            linkList.forEach {
                copyText = copyText.replace(it, "")
            }

            return emojiList.size * emojiWeight + linkList.size * linkWeight + copyText.length

        }

    }

}
