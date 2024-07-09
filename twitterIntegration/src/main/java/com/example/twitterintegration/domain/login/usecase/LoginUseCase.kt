package com.example.twitterintegration.domain.login.usecase

import com.example.twitterintegration.domain.login.TwitterLoginAuth
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val loginRepo: TwitterLoginAuth) {

    suspend  fun invoke() = loginRepo.login()

}