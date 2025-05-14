package com.example.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.model.CountryDetail
import com.example.core.model.ExchangeRate
import com.example.core.model.PriceItem
import com.example.core.repository.CountryRepository
import com.example.core.repository.PriceRepository
import com.example.core.repository.TimeZoneRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val countryRepository: CountryRepository,
    private val priceRepository: PriceRepository,
    private val timeZoneRepository: TimeZoneRepository
) : ViewModel() {

    private val _countryDetail = MutableStateFlow<CountryDetail?>(null)
    val countryDetail: StateFlow<CountryDetail?> = _countryDetail.asStateFlow()

    private val _exchangeRate = MutableStateFlow<ExchangeRate?>(null)
    val exchangeRate: StateFlow<ExchangeRate?> = _exchangeRate.asStateFlow()

    private val _priceList = MutableStateFlow<List<PriceItem>>(emptyList())
    val priceList: StateFlow<List<PriceItem>> = _priceList.asStateFlow()

    private val _travelTimeZone = MutableStateFlow<String>("")
    val travelTimeZone: StateFlow<String> = _travelTimeZone.asStateFlow()

    private val _ownTimeZone = MutableStateFlow("")
    val ownTimeZone: StateFlow<String> = _ownTimeZone.asStateFlow()

    fun loadCountryDetail(code: String) {
        viewModelScope.launch {
            val detail = countryRepository.getCountryDetail(code)
            _countryDetail.value = detail
        }
    }

    fun loadExchangeRate(base: String, target: String) {
        viewModelScope.launch {
            val result = priceRepository.getExchangeRate(base, target)
            _exchangeRate.value = result
        }
    }

    fun loadPriceList(userCountry: String, travelCountry: String) {
        viewModelScope.launch {
            val prices = priceRepository.getPriceList(userCountry, travelCountry)
            _priceList.value = prices
        }
    }

    fun loadTimeZones(ownCountryCode: String, travelCountryCode: String) {
        viewModelScope.launch {
            _ownTimeZone.value = timeZoneRepository.getTimeZone(ownCountryCode)
            _travelTimeZone.value = timeZoneRepository.getTimeZone(travelCountryCode)
        }
    }

}