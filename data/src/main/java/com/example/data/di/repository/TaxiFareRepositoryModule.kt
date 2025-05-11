package com.example.data.di.repository

import com.example.core.repository.TaxiFareRepository
import com.example.data.api.ServerApi
import com.example.data.implementation.repository.TaxiFareRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jakarta.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TaxiFareRepositoryModule {
    @Provides
    @Singleton
    fun provideTaxiFareRepository(
        serverApi: ServerApi
    ): TaxiFareRepository = TaxiFareRepositoryImpl(serverApi)
}