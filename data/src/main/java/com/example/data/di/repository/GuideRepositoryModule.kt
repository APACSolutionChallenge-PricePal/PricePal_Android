package com.example.data.di.repository

import com.example.core.repository.GuideRepository
import com.example.data.api.ServerApi
import com.example.data.implementation.repository.GuideRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object GuideRepositoryModule {
    @Provides
    @Singleton
    fun provideGuideRepository(
        serverApi: ServerApi
    ): GuideRepository = GuideRepositoryImpl(serverApi)
}