package com.example.home

import androidx.lifecycle.ViewModel

data class ExchangeRateData(
    val fromCurrency: String,  // ex: "KRW"
    val toCurrency: String,    // ex: "USD"
    val rate: Double           // ex: 0.00068
)

data class PriceItemData(
    val name: String,
    val localPrice: Int,
    val localCurrency: String,
    val convertedPrice: Double,
    val convertedCurrency: String
)

class HomeViewModel : ViewModel() {

    val exchangeRate = ExchangeRateData("KRW", "USD", 0.00012315368)

    val priceList = listOf(
        PriceItemData("A Bottle of Water", 800, "KRW", 0.54, "USD"),
        PriceItemData("Coffee", 4200, "KRW", 3.12, "USD"),
        PriceItemData("Taxi Base Fare", 3800, "KRW", 2.81, "USD")
    )
}
