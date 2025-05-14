package com.example.pricepal.test.taxi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.core.view.WindowCompat
import com.example.taxi.TaxiApp
import com.example.taxi.TaxiViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TestActivity : ComponentActivity() {

    private val viewModel: TaxiViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            TaxiApp(viewModel = viewModel)
        }

        // 필요한 테스트용 로직 작성 시 이곳에
        prepareTest()
    }

    private fun prepareTest() {
        // 예: viewModel.loadTaxiZones() 혹은 viewModel.setDestination() 등
    }
}