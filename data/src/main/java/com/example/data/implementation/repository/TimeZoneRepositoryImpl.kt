package com.example.data.implementation.repository

import com.example.core.repository.TimeZoneRepository
import com.example.data.api.TimeZoneApi
import com.example.data.api.dto.server.TimezoneRequestDTO

class TimeZoneRepositoryImpl(
    private val api: TimeZoneApi
) : TimeZoneRepository {
    override suspend fun getTimeZone(countryCode: String): String {
        val response = api.getTimeZone(TimezoneRequestDTO(countryCode))
        if (!response.isSuccess) throw Exception(response.message)
        return response.result
    }
}