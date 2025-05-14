package com.example.data.di.repository

import com.example.core.repository.CountryRepository
import com.example.data.api.ServerApi
import com.example.data.implementation.repository.CountryRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jakarta.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CountryRepositoryModule {
    @Provides
    @Singleton
    fun provideCountryRepository(
        serverApi: ServerApi
    ): CountryRepository = CountryRepositoryImpl(serverApi)
}