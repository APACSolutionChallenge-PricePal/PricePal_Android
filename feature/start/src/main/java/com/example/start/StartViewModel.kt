package com.example.start

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
//import com.example.core.model.Country
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


// Country 모델 정의 (임시)
data class Country(
    val name: String,
    val flagResId: Int
    //val flagUrl: String
)

//@HiltViewModel
//class StartViewModel @Inject constructor() : ViewModel() {
class StartViewModel : ViewModel() {

    val _countryList = MutableStateFlow<List<Country>>(
        listOf(
            Country("Korea", R.drawable.flag_kr),
            Country("Japan", R.drawable.flag_jp),
            Country("France", R.drawable.flag_fr),
            Country("USA", R.drawable.flag_us),
            Country("Germany", R.drawable.flag_de),
            Country("Italy", R.drawable.flag_it),
            Country("Canada", R.drawable.flag_ca),
            Country("United Kingdom", R.drawable.flag_gb),
        )
    )
    val countryList: StateFlow<List<Country>> = _countryList

    val _ownCountry = MutableStateFlow(_countryList.value.first())
    val ownCountry: StateFlow<Country> = _ownCountry

    val _travelCountry = MutableStateFlow(_countryList.value.first())
    val travelCountry: StateFlow<Country> = _travelCountry

    fun setOwnCountry(country: Country) {
        _ownCountry.value = country
    }

    fun setTravelCountry(country: Country) {
        _travelCountry.value = country
    }
}
