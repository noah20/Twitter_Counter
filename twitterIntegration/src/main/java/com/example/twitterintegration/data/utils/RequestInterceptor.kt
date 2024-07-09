package com.example.twitterintegration.data.utils


import com.google.gson.Gson
import okhttp3.Interceptor
import okhttp3.Response
import okio.IOException
import javax.inject.Inject

class RequestInterceptor @Inject constructor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val newRequest = chain.request().newBuilder()
//        newRequest.header("authorization", "Bearer $token")
        val response = chain.proceed(newRequest.build())
        return handleServerCodes(response)
    }

    private fun handleServerCodes(response: Response): Response {
        if(response.isSuccessful){
            return response
        }else{
            val responseBody = response.peekBody(Long.MAX_VALUE)
            val errorBody = responseBody.string()
            throw try {
                Gson().fromJson(errorBody , ServerException::class.java)
            }catch (e:Exception){
                throw ServerException(title = "unknown error",status=0)
            }
        }
    }
}