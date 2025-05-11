package com.example.data.api

import com.example.data.api.dto.BaseResponse
import com.example.data.api.dto.server.ExchangeRequestDTO
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ExchangeApi {

    @POST("/exchange/rate")
    suspend fun getExchangeRate(
        @Body request: ExchangeRequestDTO
    ): BaseResponse<Double>
}