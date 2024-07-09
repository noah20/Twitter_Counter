package com.example.twitterintegration.ui

import android.text.Editable
import android.text.TextWatcher
import com.example.tweetvalidator.tweet_validator.TweetLengthValidator


class TweetTextWatcher(private val listener:(newLength:Int)->Unit): TextWatcher {

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {


    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        if(!s.isNullOrEmpty()){
            listener(TweetLengthValidator.calculateTwitterLength(s.toString()))
        }else{
            listener(0)
        }
    }

    override fun afterTextChanged(s: Editable?) {

    }

}