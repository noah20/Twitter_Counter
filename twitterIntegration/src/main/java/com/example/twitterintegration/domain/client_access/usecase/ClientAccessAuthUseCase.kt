package com.example.twitterintegration.domain.client_access.usecase

import com.example.twitterintegration.domain.client_access.ClientAccessAuthRepo
import javax.inject.Inject

class ClientAccessAuthUseCase @Inject constructor(private val repo: ClientAccessAuthRepo) {

    suspend  fun invoke(autHeader:String,code:String,codeVerifier:String) = repo.getClientAccess(autHeader, code, codeVerifier)

}