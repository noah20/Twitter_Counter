package com.example.twitterintegration.data.model

import com.google.gson.annotations.SerializedName

data class AuthTokenHeader(
    @SerializedName("Username")
    val apiKey:String,
    @SerializedName("Password")
    val apiKeySecret:String)
