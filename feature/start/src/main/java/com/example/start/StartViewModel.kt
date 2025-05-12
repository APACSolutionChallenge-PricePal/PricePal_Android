package com.example.start

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.model.Country
import com.example.core.repository.CountryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StartViewModel @Inject constructor(
    private val countryRepository: CountryRepository
) : ViewModel() {

    private val _countryList = MutableStateFlow<List<Country>>(emptyList())
    val countryList: StateFlow<List<Country>> = _countryList

    private val _ownCountry = MutableStateFlow<Country?>(null)
    val ownCountry: StateFlow<Country?> = _ownCountry

    private val _travelCountry = MutableStateFlow<Country?>(null)
    val travelCountry: StateFlow<Country?> = _travelCountry

    init {
        loadCountries()
    }

    private fun loadCountries() {
        viewModelScope.launch {
            try {
                val countries = countryRepository.getAllCountries()
                _countryList.value = countries
                _ownCountry.value = countries.firstOrNull()
                _travelCountry.value = countries.getOrNull(1) ?: countries.firstOrNull()
            } catch (e: Exception) {
                // 실패 처리 로그 또는 상태 업데이트 (필요 시)
            }
        }
    }


    fun setOwnCountry(country: Country) {
        _ownCountry.value = country
    }

    fun setTravelCountry(country: Country) {
        _travelCountry.value = country
    }
}
