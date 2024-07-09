package com.example.twitterintegration.data.client_access.repo

import com.example.twitterintegration.data.client_access.api.TwitterClientAuthService
import com.example.twitterintegration.data.model.ClientAccessTokenResponse
import com.example.twitterintegration.domain.client_access.ClientAccessAuthRepo

class ClientAccessAuthRepoImp(private val api: TwitterClientAuthService): ClientAccessAuthRepo {

    override suspend fun getClientAccess(autHeader:String,code:String,codeVerifier:String): ClientAccessTokenResponse? {
        return api.getClientAccess(autHeader,code,codeVerifier)
    }


}