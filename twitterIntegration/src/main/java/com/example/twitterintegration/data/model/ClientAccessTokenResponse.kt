package com.example.twitterintegration.data.model

import com.google.gson.annotations.SerializedName

data class ClientAccessTokenResponse(
    @SerializedName("access_token")
    val accessToken: String,
    @SerializedName("expires_in")
    val expiresIn: Int,
    val scope: String,
    @SerializedName("token_type")
    val tokenType: String
)