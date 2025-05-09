package com.example.search.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.search.R

data class PriceItemData(
    val title: String,
    val mycityPrice: String,
    val tripPrice: String,
    val imageRes: Int
)

val sampleItems = listOf(
    PriceItemData("A Bottle of Water", "0.54 USD", "800 KRW", R.drawable.ic_bottle_blue),
    PriceItemData("A Cup of Coffee", "2.80 USD", "3900 KRW", R.drawable.ic_bottle_blue),
    PriceItemData("A Loaf of Bread", "1.20 USD", "1500 KRW", R.drawable.ic_bottle_blue)
)

@Composable
fun PriceItem(item: PriceItemData) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, Color(0xFF2E6517), RoundedCornerShape(25.dp))
            .background(Color.White, RoundedCornerShape(25.dp))
            .padding(12.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = item.imageRes),
                contentDescription = null,
                modifier = Modifier
                    .size(50.dp)
                    .padding(end = 10.dp)
            )

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(text = item.title, fontSize = 14.sp, color = Color.Black)
                Text(text = item.tripPrice, fontSize = 12.sp, color = Color.Gray)
            }

            Text(text = item.mycityPrice, fontSize = 14.sp, color = Color.Black)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PriceItemPreview() {
    PriceItem(sampleItems[0])
}