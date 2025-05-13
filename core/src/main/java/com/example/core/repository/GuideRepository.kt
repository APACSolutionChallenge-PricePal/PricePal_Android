package com.example.core.repository

import com.example.core.model.Guide

interface GuideRepository {
    suspend fun getPriceGuide(itemName: String, country: String): Guide
}
