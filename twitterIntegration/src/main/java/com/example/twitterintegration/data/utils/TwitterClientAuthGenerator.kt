package com.example.twitterintegration.data.utils

import android.os.Build
import java.nio.charset.StandardCharsets
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.security.SecureRandom
import java.util.Base64

object TwitterClientAuthGenerator {

     fun generateCodeVerifier(): String {

        val sr = SecureRandom()
        val code = ByteArray(32)
        sr.nextBytes(code)
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Base64.getUrlEncoder().withoutPadding().encodeToString(code)
        } else {
            android.util.Base64.encodeToString(code, android.util.Base64.NO_WRAP)
        }
    }

     fun generateCodeChallenge(codeVerifier: String): String {
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

     fun generateRandomState(): String {
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