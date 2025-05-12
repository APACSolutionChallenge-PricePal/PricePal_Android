package com.example.start

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material3.Text
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import com.example.start.StartViewModel

@Composable
fun StartScreen(
    //countryList: List<Country>,
    viewModel: StartViewModel = StartViewModel(),
    onGetStartedClick: (ownCountry: Country, travelCountry: Country) -> Unit
) {
//    var ownCountry by remember { mutableStateOf(countryList.first()) }
//    var travelCountry by remember { mutableStateOf(countryList.first()) }

    val countryList by viewModel.countryList.collectAsState()
    val ownCountry by viewModel.ownCountry.collectAsState()
    val travelCountry by viewModel.travelCountry.collectAsState()

    val backgroundColor = Color(0xFFFCFAF4)
    val mainColor = Color(0xFF4CAE5E)
    val titleBlack = Color(0xFF2A2E37)

    // 시스템 UI 색상 덮기
    val systemUiController = rememberSystemUiController()
    SideEffect {
        systemUiController.setSystemBarsColor(color = backgroundColor)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .padding(horizontal = 35.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column( // 상단 콘텐츠 묶기
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(72.dp))

            Text(
                text = "Please select your country.",
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium,
                color = titleBlack
            )

            Spacer(modifier = Modifier.height(37.dp))

            Column {
                CountryDropdown(
                    label = "Your Own Country",
                    selectedCountry = ownCountry,
                    countryList = countryList,
                    onCountrySelected = { viewModel.setOwnCountry(it) },
                    borderColor = mainColor
                )

                Spacer(modifier = Modifier.height(35.dp))

                CountryDropdown(
                    label = "Country to Travel",
                    selectedCountry = travelCountry,
                    countryList = countryList,
                    onCountrySelected = { viewModel.setTravelCountry(it) },
                    borderColor = mainColor
                )
            }
        }
        // 하단 버튼
        Column(
            modifier = Modifier
                .navigationBarsPadding()
        ) {
            Button(
                onClick = { onGetStartedClick(ownCountry, travelCountry) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(15.dp),
                colors = ButtonDefaults.buttonColors(containerColor = mainColor)
            ) {
                Text(text = "Get Started", color = Color.White, fontSize = 16.sp)
            }

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun StartScreenPreview() {
    val dummyViewModel = StartViewModel().apply {
        setOwnCountry(Country("Korea", R.drawable.flag_kr))
        setTravelCountry(Country("Japan", R.drawable.flag_jp))
    }

    StartScreen(
        viewModel = dummyViewModel,
        onGetStartedClick = { _, _ -> /* no-op for preview */ }
    )
}
