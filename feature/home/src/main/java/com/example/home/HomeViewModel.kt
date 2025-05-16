package com.example.home

import android.util.Log
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
            try {
                val detail = countryRepository.getCountryDetail(code)
                _countryDetail.value = detail
            } catch (e: Exception) {
                Log.e("HomeViewModel", "Error loading country detail", e)

                // 임시 더미 데이터로 앱이 멈추지 않게 유지
                _countryDetail.value = CountryDetail(
                    countryName = "NO Flag",
                    imageUrl = "https://upload.wikimedia.org/wikipedia/commons/7/75/Flag_of_None_%28square%29.svg"
                )
            }
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