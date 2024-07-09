package com.example.twitterintegration.data.utils

import com.google.gson.annotations.SerializedName
import java.io.IOException
import java.lang.Exception

data class ServerException(
    val title: String? = null,
    val detail: String? = null,
    val type: String? = null,
    val status: Int? = null
): IOException()