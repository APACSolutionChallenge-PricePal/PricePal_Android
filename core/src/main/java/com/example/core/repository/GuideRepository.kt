package com.example.core.repository

interface GuideRepository {
    suspend fun getPriceGuide(itemName: String, country: String): String
}
