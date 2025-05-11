package com.example.data.implementation.repository

import com.example.core.repository.TaxiFareRepository
import com.example.data.api.ServerApi
import com.example.data.api.dto.server.TaxiFareRequestDTO
import com.example.data.api.withCheck
import javax.inject.Inject

class TaxiFareRepositoryImpl @Inject constructor(
    private val serverApi: ServerApi
) : TaxiFareRepository {

    override suspend fun getTaxiFare(distance: String, country: String): String {
        val response = serverApi.withCheck {
            getTaxiFare(TaxiFareRequestDTO(distance, country))
        }
        return response
    }
}