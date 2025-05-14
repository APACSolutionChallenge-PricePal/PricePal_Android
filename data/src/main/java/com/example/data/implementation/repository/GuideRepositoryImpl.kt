package com.example.data.implementation.repository

import android.util.Log
import com.example.core.model.Guide
import com.example.core.repository.GuideRepository
import com.example.data.api.ServerApi
import com.example.data.api.dto.server.GuideRequestDTO
import com.example.data.api.withCheck
import javax.inject.Inject

class GuideRepositoryImpl @Inject constructor(
    private val serverApi: ServerApi
) : GuideRepository {

    override suspend fun getPriceGuide(itemName: String, country: String): Guide {
        Log.d("GuideRepository", "Sending request for item=$itemName, country=$country")

        try {
            val result = serverApi.withCheck {
                getItemGuide(GuideRequestDTO(itemName = itemName, country = country))
            }
            Log.d("GuideRepository", "Received response: $result")

            val guideDto = result.firstOrNull() ?: throw IllegalStateException("No guide returned")

            return Guide(
                itemName = itemName,
                country = country,
                content = guideDto.priceText,
                image = guideDto.image
            )
        } catch (e: Exception) {
            Log.e("GuideRepository", "Error fetching guide", e)
            throw e
        }
    }

}
