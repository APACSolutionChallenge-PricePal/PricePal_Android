package com.example.pricepal.test.home


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.core.view.WindowCompat
import androidx.lifecycle.lifecycleScope
import com.example.core.model.Country
import com.example.home.HomeApp
import com.example.home.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TestActivity : ComponentActivity() {

    private val viewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val ownCountry = Country(
                countryName = "South Korea",
                countryCode = "KR",
                downloadUrl = "https://flagcdn.com/w320/kr.png"
            )
            val travelCountry = Country(
                countryName = "Thailand",
                countryCode = "TH",
                downloadUrl = "https://flagcdn.com/w320/th.png"
            )

            HomeApp(
                viewModel = viewModel,
                ownCountry = ownCountry,
                travelCountry = travelCountry
            )
        }

        // Optional
        prepareTest()
    }

    private fun prepareTest() {
        lifecycleScope.launch {
            // 테스트용 초기 데이터가 있다면 여기에 작성
            viewModel.loadCountryDetail("TH")
            viewModel.loadExchangeRate("KRW", "THD")
            viewModel.loadPriceList("Korea", "Thailand")
        }
    }
}
