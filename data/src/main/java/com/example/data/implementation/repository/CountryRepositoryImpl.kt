package com.example.data.implementation.repository

import com.example.core.model.Country
import com.example.core.model.CountryDetail
import com.example.core.repository.CountryRepository
import com.example.data.api.ServerApi
import com.example.data.api.withCheck
import com.example.data.api.dto.server.CountryDetailRequestDTO
import javax.inject.Inject

class CountryRepositoryImpl(
    private val serverApi: ServerApi
) : CountryRepository {

    override suspend fun getAllCountries(): List<Country> {
        val response = serverApi.withCheck {
            getAllCountries()
        }

        return response.map {
            Country(
                countryName = it.countryName,
                countryCode = it.countryCode,
                downloadUrl = it.downloadUrl
            )
        }
    }

    override suspend fun getCountryDetail(code: String): CountryDetail {
        val response = serverApi.withCheck {
            getCountryDetail(CountryDetailRequestDTO(code))
        }

        val dto = response.firstOrNull()
            ?: throw IllegalStateException("Invalid response from server")

        return CountryDetail(
            countryName = dto.countryName,
            imageUrl = dto.downloadUrl
        )
    }

}