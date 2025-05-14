package com.example.data.api

import com.example.data.api.dto.BaseResponse
import com.example.data.api.*

interface ServerApi :
    CountryApi,
    ExchangeApi,
    PriceApi,
    BargainApi,
    GuideApi,
    TaxiFareApi

suspend fun <T> ServerApi.withCheck(
    getter: suspend ServerApi.() -> BaseResponse<T>
): T {
    val response = getter()
    if (!response.isSuccess) throw Exception(response.message)
    return response.result
}