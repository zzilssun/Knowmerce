package com.sample.knowmerce.core.network.di

import com.sample.knowmerce.core.network.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    @Singleton
    @Provides
    fun providePhpRetrofit(
        client: OkHttpClient,
        converter: Converter.Factory
    ): Retrofit = Retrofit.Builder()
        .apply {
            baseUrl(BuildConfig.KAKO_BASE_URL)
            client(client)
        }.apply {
            addConverterFactory(converter)
        }.build()
}