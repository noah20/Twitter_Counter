package com.example.twitterintegration.data.utils
import java.io.IOException

data class ServerException(
    val title: String? = null,
    val detail: String? = null,
    val type: String? = null,
    val status: Int? = null
): IOException()

object ErrorCodes{
    const val NOT_FOUND = 404
    const val NO_ACCESS_TOKEN_FOUND = 1001
    const val UNAUTHORIZED = 401
}
