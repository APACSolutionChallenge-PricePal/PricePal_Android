package com.example.data.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import com.example.data.api.dto.server.DirectionsResponse

interface DirectionsApi {
    @GET("directions/json")
    suspend fun getDirections(
        @Query("origin") origin: String,
        @Query("destination") destination: String,
        @Query("mode") mode: String = "transit",
        @Query("alternatives") alternatives: Boolean = true,
        @Query("key") apiKey: String
    ): Response<DirectionsResponse>
}