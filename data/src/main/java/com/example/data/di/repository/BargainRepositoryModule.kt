package com.example.data.di.repository

import com.example.core.repository.BargainRepository
import com.example.data.api.ServerApi
import com.example.data.implementation.repository.BargainRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object BargainRepositoryModule {
    @Provides
    @Singleton
    fun provideBargainRepository(
        serverApi: ServerApi
    ): BargainRepository = BargainRepositoryImpl(serverApi)
}