package com.example.data.di.repository

import com.example.core.repository.PriceRepository
import com.example.data.api.ServerApi
import com.example.data.implementation.repository.PriceRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PriceRepositoryModule {
    @Provides
    @Singleton
    fun providePriceRepository(
        serverApi: ServerApi
    ): PriceRepository = PriceRepositoryImpl(serverApi)
}