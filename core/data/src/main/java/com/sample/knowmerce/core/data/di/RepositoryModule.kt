package com.sample.knowmerce.core.data.di

import android.content.Context
import com.sample.knowmerce.core.data.api.KakaoAPIService
import com.sample.knowmerce.core.data.repository.KakaoRepository
import com.sample.knowmerce.core.data.repository.KnowMerceDatabaseRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideKakaoRepository(
        service: KakaoAPIService,
    ): KakaoRepository {
        return KakaoRepository(service)
    }

    @Singleton
    @Provides
    fun provideKnowMerceDatabaseRepository(
        @ApplicationContext context: Context
    ): KnowMerceDatabaseRepository {
        return KnowMerceDatabaseRepository(context)
    }
}