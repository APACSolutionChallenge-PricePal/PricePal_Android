package com.example.pricepal

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MainApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        // 나중에 BuildConfig 사용해서 Key값 가져오기
    }
}