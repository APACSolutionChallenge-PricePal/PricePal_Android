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
import com.example.home.HomeViewModel

val backgroundColor = Color(0xFFFCFAF4)
val mainColor = Color(0xFF4CAE5E)
val titleBlack = Color(0xFF2A2E37)
val highlight = Color(0xFF00611A)

@Composable
fun HomeScreen(viewModel: HomeViewModel) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .padding(horizontal = 25.dp, vertical = 8.dp)
    ) {
        HeaderSection()
        ExchangeRateSection(rate = viewModel.exchangeRate)
        PriceListSection(priceList = viewModel.priceList)
    }
}

@Composable
fun HeaderSection() {
    Spacer(modifier = Modifier.height(50.dp))

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
            Image(
                painter = painterResource(id = R.drawable.flag_us),
                contentDescription = "미국 국기",
                modifier = Modifier
                    .clip(RoundedCornerShape(15.dp))
                    .height(84.dp)
                    .width(112.dp),
            )
            Spacer(modifier = Modifier.width(15.dp))
            Text(
                text = "South Korea",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = titleBlack,
                maxLines = Int.MAX_VALUE,            // 제한 없이 줄 바꿈
                overflow = TextOverflow.Visible      // 잘리지 않고 계속 보여줌
            )
        }

        Spacer(modifier = Modifier.height(22.dp))

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
                    text = "U.S.A",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = "(UTC-5)",
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
                    text = "SOUTH KOREA",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = "(UTC+9)",
                    fontSize = 14.sp
                )
            }
        }
        Spacer(modifier = Modifier.height(22.dp))
    }
}

@Composable
fun ExchangeRateSection(rate: ExchangeRateData) {

    val rateText = "1 ${rate.fromCurrency} = ${rate.rate} ${rate.toCurrency}"

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
            text = rateText,
            fontSize = 32.sp,
            overflow = TextOverflow.Clip,
            softWrap = true
        )
    }
    Spacer(modifier = Modifier.height(33.dp))
}

@Composable
fun PriceListSection(priceList: List<PriceItemData>) {
    Column {
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

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            items(priceList) { item ->
                PriceItem(
                    name = item.name,
                    localPrice = item.localPrice,
                    localCurrency = item.localCurrency,
                    convertedPrice = item.convertedPrice,
                    convertedCurrency = item.convertedCurrency
                )
            }
        }
    }
}

@Composable
fun PriceItem(
    name: String,
    localPrice: Int,
    localCurrency: String,
    convertedPrice: Double,
    convertedCurrency: String
) {
    val localText = "$localPrice $localCurrency"
    val convertedText = String.format("%.2f %s", convertedPrice, convertedCurrency)

    Box(
        modifier = Modifier
            .padding(bottom = 7.dp)
            .border(2.dp, highlight, RoundedCornerShape(25.dp))
            .shadow(
                elevation = 4.dp,
                shape = RoundedCornerShape(25.dp),
                ambientColor = highlight,
                spotColor = highlight
            )
            .background(Color.White, shape = RoundedCornerShape(25.dp))
            .padding(horizontal = 22.dp, vertical = 17.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_item),
                contentDescription = "아이템 아이콘",
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
                    Text(name, fontWeight = FontWeight.SemiBold)
                    Text(localText, fontSize = 12.sp, color = Color.Gray)
                }
                Text(convertedText, fontWeight = FontWeight.Bold)
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


@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen(
        viewModel = HomeViewModel()
    )
}