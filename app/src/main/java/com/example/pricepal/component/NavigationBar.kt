package com.example.pricepal.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Icon
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.max
import androidx.compose.ui.unit.sp
import com.example.pricepal.R

enum class NavigationItem(
    @DrawableRes val icon: Int,
    val size: Size,
    val magnification: Float,
) {
    SEARCH(
        icon = R.drawable.ic_search,
        size = Size(25f, 25f),
        magnification = 1f,
    ),
    HOME(
        icon = R.drawable.ic_home,
        size = Size(25f, 25f),
        magnification = 1f,
    ),
    MAP(
        icon = R.drawable.ic_map,
        size = Size(25f, 25f),
        magnification = 1f,
    )
}
val iconHeight = 25.dp

@Composable
fun NavigationBar(
    currentNavigationItem: NavigationItem?,
    onNavigate: (NavigationItem) -> Unit,
) {
    val density = LocalDensity.current
    val padding = WindowInsets.systemBars.asPaddingValues().calculateBottomPadding()
    var maxHeight by remember { mutableStateOf<Dp?>(null) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.White)
            .clip(shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
            .shadow(
                elevation = 4.dp, // Blur 값에 대응
                spotColor = Color(0x80CACACA), // 50% 투명도 (#CA = 202, 0x80 = 50%)
                ambientColor = Color(0x80CACACA), // 필요 시 동일 설정
                shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
                clip = false // 그림자가 바깥에 생기도록
            )
    ) {
        Row(
            modifier = Modifier
                .padding(bottom = padding)
                .clip(shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
        ) {
            listOf(
                NavigationItem.SEARCH,
                NavigationItem.HOME,
                NavigationItem.MAP,
            ).forEach { item ->
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .weight(1f)
                        .clickable { onNavigate(item) }
                        .let { modifier -> maxHeight?.let{ modifier.height(it) } ?: modifier }
                        .onGloballyPositioned {
                            with(density) {
                                val height = it.size.height.toDp()
                                maxHeight = maxHeight?.let { max(it, height) } ?: run { height }
                            }
                        }
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(4.dp),
                        modifier = Modifier.padding(top = 16.dp, bottom = 8.dp),
                    ) {
                        Icon(
                            painter = painterResource(id = item.icon),
                            contentDescription = null,
                            tint = if (currentNavigationItem == item) Color(color = 0xFF00611A) else Color(color = 0xFFCACACA),
                            modifier = Modifier
                                .width(iconHeight * item.size.width / item.size.height * item.magnification)
                                .height(iconHeight * item.magnification)
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewNavigationBar() {
    NavigationBar(
        currentNavigationItem = NavigationItem.HOME,
        onNavigate = {}
    )
}