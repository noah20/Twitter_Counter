package com.example.twitterintegration.data.login.api

import com.example.twitterintegration.data.model.AuthTokenHeader
import com.example.twitterintegration.data.model.LoginResponse
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST


interface TwitterLoginApiService {

    @Headers("Content-Type: application/json")
    @FormUrlEncoded
    @POST("oauth2/token")
    suspend fun login(
        @Header("Authorization") authHeader: AuthTokenHeader = AuthTokenHeader("myzCsYas08779O5mxmHPt1NfD" ,"jhigOCsmTQUdEJ1mJqwXqonDVgKOkoTJteEGz1RQm03KPZK840"),
        @Field("grant_type") grantType: String= "client_credentials"
    ): LoginResponse?
}

