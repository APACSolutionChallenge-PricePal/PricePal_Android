package com.example.data.api

import com.example.data.api.dto.server.CountryDetailRequestDTO
import com.example.data.api.dto.server.CountryItemDTO
import com.example.data.api.dto.BaseResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface CountryApi {

    @GET("/country/all")
    suspend fun getAllCountries(): BaseResponse<List<CountryItemDTO>>

    @POST("/country/one")
    suspend fun getCountryDetail(
        @Body request: CountryDetailRequestDTO
    ): BaseResponse<String>
}