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

    fun loadCountries() {
        viewModelScope.launch {
            try {
                val countries = countryRepository.getAllCountries()
                android.util.Log.d("StartViewModel", "받아온 나라 수: ${countries.size}")
                _countryList.value = countries
                _ownCountry.value = countries.firstOrNull()
                _travelCountry.value = countries.getOrNull(1) ?: countries.firstOrNull()
            } catch (e: Exception) {
                android.util.Log.e("StartViewModel", "나라 목록 로딩 실패: ${e.message}", e)
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
