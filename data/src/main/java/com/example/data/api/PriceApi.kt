package com.example.data.api

import com.example.data.api.dto.server.ExchangeRequestDTO
import com.example.data.api.dto.server.PriceItemDTO
import com.example.data.api.dto.server.PriceRequestDTO
import com.example.data.api.dto.BaseResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface PriceApi {

//    @POST("/exchange")
//    suspend fun getExchangeRate(
//        @Body request: ExchangeRequestDTO
//    ): BaseResponse<String>

    @POST("/prices")
    suspend fun getPriceList(
        @Body request: PriceRequestDTO
    ): BaseResponse<List<PriceItemDTO>>
}