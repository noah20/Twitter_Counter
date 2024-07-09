package com.example.twitterintegration.domain.client_access

import com.example.twitterintegration.data.model.ClientAccessTokenResponse

interface ClientAccessAuthRepo {

    suspend fun getClientAccess(autHeader:String,code:String,codeVerifier:String): ClientAccessTokenResponse?
}