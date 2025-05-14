package com.example.home

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.core.model.Country
import com.example.core.model.CountryDetail
import com.example.core.model.ExchangeRate
import com.example.core.model.PriceItem
import com.example.home.HomeViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import java.text.DecimalFormat

val backgroundColor = Color(0xFFFCFAF4)
val mainColor = Color(0xFF4CAE5E)
val titleBlack = Color(0xFF2A2E37)
val highlight = Color(0xFF00611A)

@Composable
fun HomeScreen(
    viewModel: HomeViewModel,
    ownCountry: Country,
    travelCountry: Country
) {
    val countryDetail by viewModel.countryDetail.collectAsState()
    val exchangeRate by viewModel.exchangeRate.collectAsState()
    val priceList by viewModel.priceList.collectAsState()
    val ownTimeZone by viewModel.ownTimeZone.collectAsState()
    val travelTimeZone by viewModel.travelTimeZone.collectAsState()

    // 시스템 UI 색상 덮기
    val systemUiController = rememberSystemUiController()
    SideEffect {
        // 상태바 색상
        systemUiController.setStatusBarColor(backgroundColor)

        // 내비게이션 바 색상
        systemUiController.setNavigationBarColor(Color.White)
    }

    LaunchedEffect(Unit) {
        viewModel.loadCountryDetail(travelCountry.countryCode)
        viewModel.loadExchangeRate(ownCountry.countryName, travelCountry.countryName)
        viewModel.loadPriceList(ownCountry.countryName, travelCountry.countryName)
        viewModel.loadTimeZones(ownCountry.countryCode, travelCountry.countryCode)
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .systemBarsPadding()
            .padding(horizontal = 25.dp, vertical = 8.dp)
    ) {
        item {
            HeaderSection(
                travelCountry = travelCountry,
                ownCountry = ownCountry,
                countryDetail = countryDetail,
                ownTimeZone = ownTimeZone,
                travelTimeZone = travelTimeZone
            )
        }
        item { exchangeRate?.let { ExchangeRateSection(rate = it) } }
        item {
            PriceTitleSection()
        }
        items(priceList) { item ->
            PriceItem(
                name = item.itemName,
                localPrice = item.userPrice,
                convertedPrice = item.travelPrice,
                imageUrl = item.imageUrl
            )
        }
    }
}


@Composable
fun HeaderSection(
    travelCountry: Country,
    ownCountry: Country,
    countryDetail: CountryDetail?,
    ownTimeZone: String,
    travelTimeZone: String
) {
    Spacer(modifier = Modifier.height(28.dp))

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Country Info", fontSize = 16.sp, fontWeight = FontWeight.SemiBold
            )

            Spacer(modifier = Modifier.width(8.dp))

            DashedDivider(
                modifier = Modifier
                    .weight(1f)
                    .height(1.dp) // 얇은 선
            )

            Spacer(modifier = Modifier.width(8.dp))

            Image(
                painter = painterResource(id = R.drawable.ic_refresh),
                contentDescription = "새로고침 아이콘",
                modifier = Modifier.size(20.dp)
            )
        }

        Spacer(modifier = Modifier.height(28.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 11.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            if (countryDetail != null) {
                coil3.compose.AsyncImage(
                    model = countryDetail.imageUrl,
                    contentDescription = "${countryDetail.countryName} 국기",
                    modifier = Modifier
                        .clip(RoundedCornerShape(15.dp))
                        .height(84.dp)
                        .width(112.dp)
                )
            }
            Spacer(modifier = Modifier.width(15.dp))
            if (countryDetail != null) {
                Text(
                    text = countryDetail.countryName,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = titleBlack,
                    maxLines = Int.MAX_VALUE,
                    overflow = TextOverflow.Visible
                )
            }
        }

        Spacer(modifier = Modifier.height(22.dp))

        // 여행지 / 내 나라 정보 (시계 UI)
        TimezoneRow(
            ownCountry = ownCountry,
            travelCountry = travelCountry,
            ownTimeZone = ownTimeZone,          // 서버에서 받으면 교체 가능
            travelTimeZone = travelTimeZone
        )


        Spacer(modifier = Modifier.height(22.dp))
    }
}
@Composable
fun TimezoneRow(
    ownCountry: Country,
    travelCountry: Country,
    ownTimeZone: String,
    travelTimeZone: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // 왼쪽
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(end = 8.dp), // 시계와 간격 보장
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = travelCountry.countryName.uppercase(),
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = "($travelTimeZone)",
                fontSize = 14.sp
            )
        }

        // 시계 아이콘
        Image(
            painter = painterResource(id = R.drawable.ic_clock),
            contentDescription = "시계 아이콘",
            modifier = Modifier
                .size(24.dp)
        )

        // 오른쪽
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(start = 8.dp), // 시계와 간격 보장
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = ownCountry.countryName.uppercase(),
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = "($ownTimeZone)",
                fontSize = 14.sp
            )
        }
    }
}


@Composable
fun ExchangeRateSection(rate: ExchangeRate) {

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Exchange Rates", fontSize = 16.sp, fontWeight = FontWeight.SemiBold, color = titleBlack)

            Spacer(modifier = Modifier.width(8.dp))

            DashedDivider(
                modifier = Modifier
                    .weight(1f)
                    .height(1.dp) // 얇은 선
            )
        }

        Spacer(modifier = Modifier.height(31.dp))


        Text(
            text = rate.rateText,
            fontSize = 32.sp,
            overflow = TextOverflow.Clip,
            softWrap = true
        )
    }
    Spacer(modifier = Modifier.height(33.dp))
}

@Composable
fun PriceTitleSection() {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            "Prices",
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            color = titleBlack
        )

        Spacer(modifier = Modifier.width(8.dp))

        DashedDivider(
            modifier = Modifier
                .weight(1f)
                .height(1.dp) // 얇은 선
        )
    }
    Spacer(modifier = Modifier.height(30.dp))
}

@Composable
fun PriceItem(
    name: String,
    localPrice: String,
    convertedPrice: String,
    imageUrl: String
) {
    Box(
        modifier = Modifier
            .padding(bottom = 7.dp)
            .border(2.dp, mainColor, RoundedCornerShape(25.dp))
            .shadow(
                elevation = 4.dp,
                shape = RoundedCornerShape(25.dp),
                ambientColor = mainColor,
                spotColor = mainColor
            )
            .background(Color.White, shape = RoundedCornerShape(25.dp))
            .padding(horizontal = 22.dp, vertical = 17.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            coil3.compose.AsyncImage(
                model = imageUrl,
                contentDescription = "$name 이미지",
                modifier = Modifier
                    .size(75.dp)
                    .clip(CircleShape)
            )
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(name, fontSize = 20.sp)
                    Text(localPrice, fontSize = 16.sp, color = Color.Gray)
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(convertedPrice, fontSize = 18.sp)
                    }
                }

            }

        }
    }
}

@Composable
fun DashedDivider(
    color: Color = highlight,
    thickness: Dp = 0.5.dp,
    dashLength: Float = 6f,
    gapLength: Float = 4f,
    modifier: Modifier = Modifier
        .fillMaxWidth()
        .height(thickness)
) {
    Canvas(modifier = modifier) {
        drawLine(
            color = color,
            start = Offset(0f, size.height / 2),
            end = Offset(size.width, size.height / 2),
            strokeWidth = thickness.toPx(),
            pathEffect = PathEffect.dashPathEffect(floatArrayOf(dashLength, gapLength))
        )
    }
}


//@Preview(showBackground = true)
//@Composable
//fun HomeScreenPreview() {
//    HomeScreen(
//        viewModel = HomeViewModel()
//    )
//}