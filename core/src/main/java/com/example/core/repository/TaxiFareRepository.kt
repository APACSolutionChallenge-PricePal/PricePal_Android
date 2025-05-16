package com.example.core.repository

import com.example.core.model.TaxiFare

interface TaxiFareRepository {
    suspend fun getTaxiFare(distance: String, country: String): TaxiFare
}
