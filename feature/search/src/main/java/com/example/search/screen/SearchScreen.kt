package com.example.search.screen

import android.view.WindowInsets
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.search.R
import com.example.search.SearchViewModel
import com.example.search.components.PriceItem
import com.google.accompanist.systemuicontroller.rememberSystemUiController


val backgroundColor = Color(0xFFFCFAF4)
val mainColor = Color(0xFF4CAE5E)
val mainText = Color(0xFF4B4B4B)
val titleBlack = Color(0xFF2A2E37)
val highlight = Color(0xFF00611A)
val white = Color(0xFFFFFFFF)
val lightgray = Color(0xFFCACACA)


@Composable
fun SearchScreen(
    viewModel: SearchViewModel,
    navController: NavController
) {
    val scrollState = rememberScrollState()
    val tips by viewModel.tips.collectAsState()
    val summary by viewModel.summary.collectAsState()
    var localQuery by remember { mutableStateOf(TextFieldValue("")) }
    val priceItems by viewModel.priceItemDataList.collectAsState()

    // 시스템 UI 색상 덮기
    val systemUiController = rememberSystemUiController()
    SideEffect {
        // 상태바 색상
        systemUiController.setStatusBarColor(backgroundColor)

        // 내비게이션 바 색상
        systemUiController.setNavigationBarColor(Color.White)
    }
    Column(
        modifier = Modifier
            .background(backgroundColor)
            .fillMaxSize()
            .padding(bottom = 20.dp)
            .verticalScroll(scrollState)
    ) {
        // 검색창
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 59.dp, start = 36.dp, end = 36.dp)
        ) {
            Box(
                modifier = Modifier.fillMaxWidth()
            ) {
                SearchBar(
                    query = localQuery,
                    onQueryChange = {
                        localQuery = it
                    },
                    onSearch = {
                        viewModel.updateSearchQuery(localQuery.text)
                        navController.navigate("result")
                    }
                )
            }
        }

        // 흥정하는 말
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "Bargaining Words",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = mainText,
                    modifier = Modifier.padding(start = 5.dp)
                )
                Spacer(modifier = Modifier.width(11.dp))
                Canvas(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(0.5.dp)
                        .align(Alignment.CenterVertically)
                ) {
                    val dashEffect = PathEffect.dashPathEffect(floatArrayOf(5f, 5f), 0f)
                    drawLine(
                        color = highlight,
                        start = Offset(0f, 0f),
                        end = Offset(size.width, 0f),
                        strokeWidth = 2f,
                        pathEffect = dashEffect
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // tips 출력
            tips.forEach {
                Text(
                    text = "• ${it.english}\n   ${it.local}\n   ${it.romanization}",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = mainText,
                    lineHeight = 23.sp,
                    modifier = Modifier.padding(vertical = 4.dp)
                )
            }

            Spacer(modifier = Modifier.height(19.dp))

            // 요약 출력
            Text(
                text = summary,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = mainText,
                lineHeight = 23.sp
            )
        }

        // Price 제목 + 리스트
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp)
            ) {
                Text(
                    text = "Prices",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = titleBlack,
                    modifier = Modifier.padding(start = 5.dp)
                )

                Spacer(modifier = Modifier.width(23.dp))

                Canvas(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(0.5.dp)
                        .align(Alignment.CenterVertically)
                ) {
                    val dashEffect = PathEffect.dashPathEffect(floatArrayOf(5f, 5f), 0f)
                    drawLine(
                        color = highlight,
                        start = Offset(0f, 0f),
                        end = Offset(size.width, 0f),
                        strokeWidth = 2f,
                        pathEffect = dashEffect
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            priceItems.take(3).forEach { item ->
                PriceItem(item = item)
                Spacer(modifier = Modifier.height(7.dp))
            }
        }
    }

    LaunchedEffect(Unit) {
        val country = viewModel.searchQuery.value
        if (country.isNotEmpty()) {
            viewModel.fetchBargainInfo(country)
            viewModel.fetchPrices(userCountry = "Korea", travelCountry = country)
        }
    }
}

@Composable
fun SearchBar(
    query: TextFieldValue,
    onQueryChange: (TextFieldValue) -> Unit,
    onSearch: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            contentAlignment = Alignment.CenterStart,
            modifier = Modifier
                .weight(1f)
                .height(44.dp)
                .border(2.dp, highlight, RoundedCornerShape(20.dp))
                .background(white, RoundedCornerShape(20.dp))
                .padding(horizontal = 24.dp, vertical = 13.dp)
        ) {
            if (query.text.isBlank()) {
                androidx.compose.material.Text(
                    text = "search ...",
                    color = lightgray,
                    fontSize = 15.sp
                )
            }

            BasicTextField(
                value = query,
                onValueChange = { onQueryChange(it) }, // 그대로 넘겨주기
                textStyle = TextStyle(color = mainText, fontSize = 15.sp),
                singleLine = true,
                cursorBrush = SolidColor(mainText),
                modifier = Modifier.fillMaxWidth()
            )
        }

        Spacer(modifier = Modifier.width(10.dp))

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(25.dp)
                .clickable { onSearch() } // 클릭 시 검색
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_search),
                contentDescription = "Search Icon",
                modifier = Modifier.size(25.dp)
            )
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun SearchScreenPreview() {
//    val fakeNavController = rememberNavController()
//    SearchScreen(navController = fakeNavController)
//}