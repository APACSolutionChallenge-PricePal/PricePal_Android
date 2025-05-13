package com.example.core.model

data class TaxiFare(
    val country: String,
    val distance: String,
    val fareInfo: String // "Basic Taxi Fares: -\nEstimated Fare: EGP 30-45\n"
)