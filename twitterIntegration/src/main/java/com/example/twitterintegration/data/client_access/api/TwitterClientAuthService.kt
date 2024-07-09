package com.example.twitterintegration.data.client_access.api

import com.example.twitterintegration.data.model.ClientAccessTokenResponse
import com.example.twitterintegration.data.utils.ApiKeys
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST


interface TwitterClientAuthService {

    @Headers("Content-Type: application/x-www-form-urlencoded")
    @FormUrlEncoded
    @POST("2/oauth2/token")
    suspend fun getClientAccess(
        @Header("Authorization") autHeader:String,
        @Field("code") code: String ,
        @Field("code_verifier") codeVerifier: String ,
        @Field("grant_type") grantType: String= "authorization_code",
        @Field("redirect_uri") redirectUrl: String= ApiKeys.redirectUrl,
        @Field("client_id") clientId: String= ApiKeys.clientId,
    ): ClientAccessTokenResponse?
}

