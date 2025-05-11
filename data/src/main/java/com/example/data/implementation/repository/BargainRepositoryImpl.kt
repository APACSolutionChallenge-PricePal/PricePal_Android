package com.example.data.implementation.repository

import com.example.core.model.Tip
import com.example.core.repository.BargainRepository
import com.example.data.api.ServerApi
import com.example.data.api.dto.server.BargainRequestDTO
import com.example.data.api.withCheck
import javax.inject.Inject

class BargainRepositoryImpl @Inject constructor(
    private val serverApi: ServerApi
) : BargainRepository {

    override suspend fun getBargainTips(travelCountry: String): List<Tip> {
        val response = serverApi.withCheck {
            getBargainTips(BargainRequestDTO(travelCountry))
        }
        return response.tips.map {
            Tip(
                romanization = it.romanization,
                local = it.local,
                english = it.english
            )
        }
    }

    override suspend fun getBargainSummary(travelCountry: String): String {
        val response = serverApi.withCheck {
            getBargainTips(BargainRequestDTO(travelCountry))
        }
        return response.summary
    }

}