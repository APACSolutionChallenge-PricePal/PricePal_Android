package com.example.data.di.repository

import com.example.core.repository.TimeZoneRepository
import com.example.data.api.ServerApi
import com.example.data.api.TimeZoneApi
import com.example.data.implementation.repository.TimeZoneRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TimeZoneRepositoryModule {
    @Provides
    @Singleton
    fun provideTimeZoneRepository(
        serverApi: ServerApi
    ): TimeZoneRepository = TimeZoneRepositoryImpl(serverApi)
}