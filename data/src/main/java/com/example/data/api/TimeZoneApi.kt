package com.example.data.api

import com.example.data.api.dto.BaseResponse
import com.example.data.api.dto.server.TimezoneRequestDTO
import retrofit2.http.Body
import retrofit2.http.POST

interface TimeZoneApi {

    @POST("/timezone")
    suspend fun getTimeZone(
        @Body request: TimezoneRequestDTO
    ): BaseResponse<String>
}