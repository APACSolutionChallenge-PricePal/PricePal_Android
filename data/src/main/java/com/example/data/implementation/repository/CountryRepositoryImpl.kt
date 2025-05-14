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
        val raw = serverApi.withCheck {
            getCountryDetail(CountryDetailRequestDTO(code))
        }

        // 예: "[{download_url=..., country_eng_nm=...}]"
        val urlRegex = "download_url=(.*?),".toRegex()
        val nameRegex = "country_eng_nm=(.*?)\\]".toRegex()

        val imageUrl = urlRegex.find(raw)?.groups?.get(1)?.value?.trim() ?: ""
        val countryName = nameRegex.find(raw)?.groups?.get(1)?.value?.trim() ?: ""

        return CountryDetail(
            countryName = countryName,
            imageUrl = imageUrl
        )
    }

}