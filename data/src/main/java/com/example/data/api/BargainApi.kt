package com.example.data.api

import com.example.data.api.dto.server.BargainRequestDTO
import com.example.data.api.dto.server.BargainResponseDTO
import com.example.data.api.dto.BaseResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface BargainApi {

    @POST("/bargain")
    suspend fun getBargainTips(
        @Body request: BargainRequestDTO
    ): BaseResponse<BargainResponseDTO>
}