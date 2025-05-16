package com.example.core

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.core.model.Country
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {
    private val _ownCountry = mutableStateOf<Country?>(null)
    val ownCountry: State<Country?> get() = _ownCountry

    private val _travelCountry = mutableStateOf<Country?>(null)
    val travelCountry: State<Country?> get() = _travelCountry

    fun setCountries(own: Country, travel: Country) {
        _ownCountry.value = own
        _travelCountry.value = travel
    }
}