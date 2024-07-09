package com.example.twitterintegration.ui

import android.util.Base64
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.tweetvalidator.tweet_validator.TweetLengthValidator
import com.example.twitterintegration.data.model.PostTweetRequest
import com.example.twitterintegration.data.utils.ApiKeys
import com.example.twitterintegration.data.utils.ErrorCodes.UNAUTHORIZED
import com.example.twitterintegration.data.utils.ServerException
import com.example.twitterintegration.data.utils.SharedPrefs
import com.example.twitterintegration.domain.client_access.usecase.ClientAccessAuthUseCase
import com.example.twitterintegration.domain.post_tweet.usecase.PostTweetUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostTwitViewModel @Inject constructor(
    private val service: PostTweetUseCase,
    private val clientAccess: ClientAccessAuthUseCase,
    private val sharedPref: SharedPrefs,

    ) : ViewModel() {

    fun postTweet(txt: String , token:String = "") = liveData(Dispatchers.IO) {
        try {
            val accessToken = sharedPref.getClientAccessToken()
            if(accessToken.isNullOrBlank())
                throw ServerException(title = "unauthorized",status= UNAUTHORIZED)
            service.execute(PostTweetRequest(txt),"Bearer $accessToken")
            emit(Result.success(true))
        } catch (e: Exception) {
            e.printStackTrace()
            if(e is ServerException){
                emit(Result.failure(e))
            }else
                emit(Result.failure(e))
        }
    }

    fun isTweetValid(tweet: String, maxTweetLength: Int): Boolean {
        val actualLength = TweetLengthValidator.calculateTwitterLength(tweet)
        return (maxTweetLength - actualLength) >= 0
    }

    fun saveGeneratedCode(verifyCode:String){
        sharedPref.saveGeneratedCode(verifyCode)

    }

    fun getGeneratedCode() = sharedPref.getGeneratedCode()

    fun doClientAuth(code:String ,codeVerifier:String) = liveData<Result<Boolean>> {
        try {
            val apiKey = ApiKeys.apiKey
            val apiKeySecret = ApiKeys.apiKeySecret
            val basic = "$apiKey:$apiKeySecret"
            val authHeader = "basic ${Base64.encodeToString(basic.toByteArray(),Base64.NO_WRAP)}"
            val d = clientAccess.invoke(authHeader,code= code ,codeVerifier)
            sharedPref.saveClientAccessToken(d?.accessToken)
            service.execute(PostTweetRequest("Hello World!"),"Bearer ${d?.accessToken ?: ""}")
            emit(Result.success(true))
        }catch (e:Exception){
            e.printStackTrace()
            emit(Result.failure(e))
        }

    }
}