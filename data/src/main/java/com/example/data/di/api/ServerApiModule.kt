package com.example.data.di.api

import com.example.data.api.ServerApi
import com.squareup.moshi.Moshi
import com.example.data.BuildConfig
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServerApiModule {

    @Provides
    @Singleton
    fun provideServerApi(): ServerApi {
        val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
        val logger = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val client = OkHttpClient.Builder()
            .addInterceptor(logger)
            .build()

        return Retrofit.Builder()
            .baseUrl(BuildConfig.SERVER_BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(client)
            .build()
            .create(ServerApi::class.java)
    }

}
