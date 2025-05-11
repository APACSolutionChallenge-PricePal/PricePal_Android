package com.example.data.api

import com.example.data.api.dto.server.TaxiFareRequestDTO
import com.example.data.api.dto.BaseResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface TaxiFareApi {

    @POST("/temp/taxifare")
    suspend fun getTaxiFare(
        @Body request: TaxiFareRequestDTO
    ): BaseResponse<String>
}
