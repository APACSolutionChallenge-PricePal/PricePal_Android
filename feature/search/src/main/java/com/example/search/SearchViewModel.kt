package com.example.search

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class SearchViewModel : ViewModel() {
    var searchQuery = mutableStateOf("")
        private set

    fun updateSearchQuery(newQuery: String) {
        searchQuery.value = newQuery
    }
}