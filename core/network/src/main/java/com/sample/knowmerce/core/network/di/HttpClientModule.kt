package com.sample.knowmerce.core.network.di

import com.sample.knowmerce.core.network.interceptor.KakaoInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HttpClientModule {

    @Singleton
    @Provides
    fun provideClient(): OkHttpClient =
        OkHttpClient.Builder()
            .apply {
                connectTimeout(30, TimeUnit.MINUTES)
                readTimeout(30, TimeUnit.MINUTES)
                writeTimeout(30, TimeUnit.SECONDS)
            }
            .apply {
                addInterceptor(KakaoInterceptor())
            }
            .build()
}