package com.example.core.repository

import com.example.core.model.Tip

interface BargainRepository {
    suspend fun getBargainTips(travelCountry: String): List<Tip>
    suspend fun getBargainSummary(travelCountry: String): String
}
