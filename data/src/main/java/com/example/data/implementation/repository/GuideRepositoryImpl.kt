package com.example.data.implementation.repository

import com.example.core.repository.GuideRepository
import com.example.data.api.ServerApi
import com.example.data.api.dto.server.GuideRequestDTO
import com.example.data.api.withCheck
import javax.inject.Inject

class GuideRepositoryImpl @Inject constructor(
    private val serverApi: ServerApi
) : GuideRepository {

    override suspend fun getPriceGuide(itemName: String, country: String): String {
        val response = serverApi.withCheck {
            getItemGuide(GuideRequestDTO(itemName = itemName, country = country))
        }
        return response
    }
}
