package com.example.core.repository

import com.example.core.model.Country
import com.example.core.model.CountryDetail

interface CountryRepository {
    suspend fun getAllCountries(): List<Country>
    suspend fun getCountryDetail(code: String): CountryDetail
}
