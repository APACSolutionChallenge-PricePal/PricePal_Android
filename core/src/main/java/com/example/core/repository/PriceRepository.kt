package com.example.core.repository

import com.example.core.model.PriceItem

interface PriceRepository {
    suspend fun getExchangeRate(base: String, target: String): Double
    suspend fun getPriceList(userCountry: String, travelCountry: String): List<PriceItem>
}
