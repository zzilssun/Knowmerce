package com.sample.knowmerce.core.data.di

import com.sample.knowmerce.core.data.api.KakaoAPIService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object APIServiceModule {

    @Singleton
    @Provides
    fun provideKakaoAPIService(retrofit: Retrofit): KakaoAPIService {
        return retrofit.create(KakaoAPIService::class.java)
    }
}