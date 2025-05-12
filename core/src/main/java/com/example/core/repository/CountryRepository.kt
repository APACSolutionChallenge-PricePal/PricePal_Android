package com.example.core.repository

import com.example.core.model.Country

interface CountryRepository {
    suspend fun getAllCountries(): List<Country>
    suspend fun getCountryDetail(code: String): String
}
