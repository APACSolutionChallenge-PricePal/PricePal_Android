package com.example.search.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.search.R
import com.example.search.model.PriceItemData
import com.example.search.screen.highlight

val mainText = Color(0xFF4B4B4B)
val highlight = Color(0xFF00611A)
val gray = Color(0xFF838383)
val white = Color(0xFFFFFFFF)


val sampleItems = listOf(
    PriceItemData("A Bottle of Water", "0.54 USD", "800 KRW", "https://images.unsplash.com/photo-1604882406195-d94d4888567d?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w3NDkxODh8MHwxfHNlYXJjaHwxfHxSb2FzdGVkJTIwU2Vhd2VlZCUyMFNuYWNrJTIwJTI1MjhHaW0lMjUyOXxlbnwwfHx8fDE3NDcyNDkyNTh8MA&ixlib=rb-4.1.0&q=80&w=1080"),
    PriceItemData("A Cup of Coffee", "2.80 USD", "3900 KRW", "https://images.unsplash.com/photo-1604882406195-d94d4888567d?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w3NDkxODh8MHwxfHNlYXJjaHwxfHxSb2FzdGVkJTIwU2Vhd2VlZCUyMFNuYWNrJTIwJTI1MjhHaW0lMjUyOXxlbnwwfHx8fDE3NDcyNDkyNTh8MA&ixlib=rb-4.1.0&q=80&w=1080"),
    PriceItemData("A Loaf of Bread", "1.20 USD", "1500 KRW", "https://images.unsplash.com/photo-1604882406195-d94d4888567d?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w3NDkxODh8MHwxfHNlYXJjaHwxfHxSb2FzdGVkJTIwU2Vhd2VlZCUyMFNuYWNrJTIwJTI1MjhHaW0lMjUyOXxlbnwwfHx8fDE3NDcyNDkyNTh8MA&ixlib=rb-4.1.0&q=80&w=1080")
)

@Composable
fun PriceItem(item: PriceItemData) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, highlight, RoundedCornerShape(25.dp))
            .background(white, RoundedCornerShape(25.dp))
            .padding(horizontal = 22.dp, vertical = 17.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = item.image,
                contentDescription = item.itemName,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(75.dp)
                    .clip(CircleShape) // ✅ 원형 처리
                    .background(Color.LightGray) // 원 안 배경 깔끔하게
            )

            Spacer(modifier = Modifier.width(16.dp)) // ✅ 바깥 여백으로 처리

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Column {
                    Text(
                        text = item.itemName,
                        fontSize = 20.sp,
                        color = mainText
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = item.travelPrice,
                        fontSize = 16.sp,
                        color = gray
                    )
                }

                Spacer(modifier = Modifier.height(1.dp))

                Text(
                    text = item.userPrice,
                    fontSize = 18.sp,
                    color = mainText,
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentWidth(Alignment.End)
                        .padding(end = 3.dp, bottom = 2.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PriceItemPreview() {
    PriceItem(sampleItems[0])
}