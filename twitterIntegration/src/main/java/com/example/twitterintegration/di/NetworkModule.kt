package com.example.twitterintegration.di


import com.example.twitterintegration.data.login.api.TwitterLoginApiService
import com.example.twitterintegration.data.login.repo.TweetLoginAuthImp
import com.example.twitterintegration.data.utils.RequestInterceptor
import com.example.twitterintegration.data.post_tweet.api.TwitterApiService
import com.example.twitterintegration.data.post_tweet.repo.PostTweetRepositoryImp
import com.example.twitterintegration.domain.login.TwitterLoginAuth
import com.example.twitterintegration.domain.post_tweet.PostTweetRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModuleProvider {

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.twitter.com/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideOkHttp(requestInterceptor: RequestInterceptor) : OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient.Builder().apply {
            connectTimeout(60, TimeUnit.SECONDS)
            readTimeout(60, TimeUnit.SECONDS)
            writeTimeout(60, TimeUnit.SECONDS)
            addInterceptor(requestInterceptor)
            addInterceptor(interceptor)
        }.build()
    }

    @Provides
    @Singleton
    fun provideTwitterApiService(retrofit:Retrofit): TwitterApiService {
        return retrofit.create(TwitterApiService::class.java)
    }


    @Provides
    @Singleton
    fun providePostTweetRepository(twitterApiService: TwitterApiService): PostTweetRepository {
        return PostTweetRepositoryImp(twitterApiService)
    }

    @Provides
    @Singleton
    fun provideTwitterLoginApiService(retrofit:Retrofit): TwitterLoginApiService {
        return retrofit.create(TwitterLoginApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideTweetLoginAuthImp(twitterApiService: TwitterLoginApiService): TwitterLoginAuth {
        return TweetLoginAuthImp(twitterApiService)
    }

}