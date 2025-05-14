package com.example.core.repository

interface TaxiFareRepository {
    suspend fun getTaxiFare(distance: String, country: String): String
}
