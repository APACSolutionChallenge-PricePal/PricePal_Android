package com.example.data.di.repository

import com.example.core.repository.DirectionsRepository
import com.example.data.api.DirectionsApi
import com.example.data.implementation.repository.DirectionsRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DirectionsRepositoryModule {

    @Provides
    fun provideDirectionsRepository(
        directionsApi: DirectionsApi
    ): DirectionsRepository {
        return DirectionsRepositoryImpl(directionsApi)
    }
}
