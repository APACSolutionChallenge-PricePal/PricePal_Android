package com.example.search

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.model.PriceItem
import com.example.core.model.Tip
import com.example.core.repository.BargainRepository
import com.example.core.repository.PriceRepository
import com.example.search.model.PriceItemData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val bargainRepository: BargainRepository,
    private val priceRepository: PriceRepository
) : ViewModel() {

    var searchQuery = mutableStateOf("")
        private set

    fun updateSearchQuery(newQuery: String) {
        searchQuery.value = newQuery
    }

    private val _tips = MutableStateFlow<List<Tip>>(emptyList())
    val tips: StateFlow<List<Tip>> = _tips

    private val _summary = MutableStateFlow("")
    val summary: StateFlow<String> = _summary

    private val _priceItems = MutableStateFlow<List<PriceItem>>(emptyList())
    val priceItems: StateFlow<List<PriceItem>> = _priceItems

    // ✅ 변환된 UI 모델을 제공하는 StateFlow
    val priceItemDataList: StateFlow<List<PriceItemData>> =
        _priceItems.map { items ->
            items.map {
                PriceItemData(
                    itemName = it.itemName,
                    userPrice = it.userPrice,
                    travelPrice = it.travelPrice,
                    image = it.imageUrl
                )
            }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun fetchBargainInfo(country: String) {
        viewModelScope.launch {
            try {
                _tips.value = bargainRepository.getBargainTips(country)
                _summary.value = bargainRepository.getBargainSummary(country)
            } catch (e: Exception) {
                // 오류 처리
                _summary.value = "Failed to load bargaining tips."
            }
        }
    }

    fun fetchPrices(userCountry: String, travelCountry: String) {
        viewModelScope.launch {
            try {
                _priceItems.value = priceRepository.getPriceList(userCountry, travelCountry)
            } catch (e: Exception) {
                _priceItems.value = emptyList()
            }
        }
    }
}