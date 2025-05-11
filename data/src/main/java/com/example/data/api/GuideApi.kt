package com.example.data.api

import com.example.data.api.dto.server.GuideRequestDTO
import com.example.data.api.dto.BaseResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface GuideApi {

    @POST("/temp/guide")
    suspend fun getItemGuide(
        @Body request: GuideRequestDTO
    ): BaseResponse<String>
}