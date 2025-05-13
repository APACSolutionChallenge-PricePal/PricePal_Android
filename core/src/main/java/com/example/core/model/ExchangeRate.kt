package com.example.core.model

data class ExchangeRate(
    val fromCurrency: String,
    val toCurrency: String,
    val rate: Double
)