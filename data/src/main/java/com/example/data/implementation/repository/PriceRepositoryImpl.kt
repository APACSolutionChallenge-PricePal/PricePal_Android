package com.example.data.implementation.repository

import com.example.core.model.ExchangeRate
import com.example.core.model.PriceItem
import com.example.core.repository.PriceRepository
import com.example.data.api.ServerApi
import com.example.data.api.dto.server.ExchangeRequestDTO
import com.example.data.api.dto.server.PriceRequestDTO
import com.example.data.api.withCheck
import javax.inject.Inject

class PriceRepositoryImpl @Inject constructor(
    private val serverApi: ServerApi
) : PriceRepository {

    override suspend fun getExchangeRate(base: String, target: String): ExchangeRate {
        val responseString = serverApi.withCheck {
            getExchangeRate(ExchangeRequestDTO(base, target))
        }
        return ExchangeRate(rateText = responseString)
    }

    override suspend fun getPriceList(userCountry: String, travelCountry: String): List<PriceItem> {
        val response = serverApi.withCheck {
            getPriceList(PriceRequestDTO(userCountry = userCountry, travelCountry = travelCountry))
        }
        return response.map {
            PriceItem(
                itemName = it.itemName,
                userPrice = it.userPrice,
                travelPrice = it.travelPrice,
                imageUrl = it.image
            )
        }
    }
}
