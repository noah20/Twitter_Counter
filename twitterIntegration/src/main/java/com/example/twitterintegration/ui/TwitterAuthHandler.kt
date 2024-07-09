package com.example.twitterintegration.ui

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.twitterintegration.data.utils.ApiKeys
import java.nio.charset.StandardCharsets
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.security.SecureRandom
import java.util.Base64


open class TwitterAuthHandler:Fragment() {

    private var codeVerifier: String = ""

    private val viewModel: PostTwitViewModel by viewModels()




    fun startAuthUser(){
        codeVerifier = generateCodeVerifier()

        val clientId = ApiKeys.clientId
        val redirectUri = ApiKeys.redirectUrl
        val state: String = generateRandomState() // Generate a random state for CSRF protection
        val codeChallenge: String = generateCodeChallenge(codeVerifier)
        val responseType = "code"
        viewModel.saveGeneratedCode(codeVerifier)
        val authorizationUrl = ApiKeys.buildClientAuth(
            responseType = responseType,
            clientId = clientId,
            redirectUrl = redirectUri,
            state = state,
            codeChallenge = codeChallenge
        )
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(authorizationUrl))

        startActivity(intent)
    }

    override fun onResume() {
        super.onResume()
        Log.d("TAG", "onResume: noah here new data ${activity?.intent?.data}")
        activity?.intent?.let {
            handleIntent(it)
        }
    }

    fun openTweeterIntent(tweet: String) {
        val tweetUrl = (ApiKeys.POST_TWEET_URL.plus(tweet))
        val uri = Uri.parse(tweetUrl)
        startActivity(Intent(Intent.ACTION_VIEW, uri))
    }

    private fun handleIntent(intent: Intent) {
        val uri = intent.data
        if (uri != null && uri.toString().startsWith(ApiKeys.redirectUrl)) {
            val code = uri.getQueryParameter("code")
            if (code != null) {
                exchangeCodeForAccessToken(code)
            }
        }
    }

    private fun exchangeCodeForAccessToken(code: String) {
        viewModel.doClientAuth(code , viewModel.getGeneratedCode() ?: "")
    }

    private fun generateCodeVerifier(): String {
        val sr = SecureRandom()
        val code = ByteArray(32)
        sr.nextBytes(code)
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Base64.getUrlEncoder().withoutPadding().encodeToString(code)
        } else {
            android.util.Base64.encodeToString(code, android.util.Base64.NO_WRAP)
        }
    }

    private fun generateCodeChallenge(codeVerifier: String): String {
        try {
            val digest = MessageDigest.getInstance("SHA-256")
            val hash = digest.digest(codeVerifier.toByteArray(StandardCharsets.UTF_8))
            return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                Base64.getUrlEncoder().withoutPadding().encodeToString(hash)
            } else {
                android.util.Base64.encodeToString(hash, android.util.Base64.NO_WRAP)
            }
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        }
        return ""
    }

    private fun generateRandomState(): String {
        val random = SecureRandom()
        val bytes = ByteArray(32)
        random.nextBytes(bytes)
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Base64.getUrlEncoder().withoutPadding().encodeToString(bytes)
        } else {
            android.util.Base64.encodeToString(bytes, android.util.Base64.NO_WRAP)
        }
    }
}