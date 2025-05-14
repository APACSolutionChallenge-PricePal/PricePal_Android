package com.example.core.repository

interface TimeZoneRepository {
    suspend fun getTimeZone(countryCode: String): String
}