package com.sample.knowmerce.core.network.di

import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Converter
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ModelConverterModule {

    @Singleton
    @Provides
    fun provideGsonConverter(): Converter.Factory {
        return GsonConverterFactory.create(GsonBuilder().create())
    }
}