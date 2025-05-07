package com.example.start

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade

@Composable
fun CountryDropdown(
    label: String,
    selectedCountry: Country,
    countryList: List<Country>, // 🔥 외부에서 리스트 받기!
    onCountrySelected: (Country) -> Unit,
    borderColor: Color
) {
    var expanded by remember { mutableStateOf(false) }
    var dropdownWidth by remember { mutableStateOf(0) }

    val titleBlack = Color(0xFF2A2E37)
    val mainText = Color(0xFF4B4B4B)

    Column {
        Text(
            text = label,
            fontSize = 16.sp,
            color = titleBlack,
            modifier = Modifier.padding(bottom = 10.dp)
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .onGloballyPositioned { coordinates ->
                    dropdownWidth = coordinates.size.width
                }
                .border(2.dp, borderColor, RoundedCornerShape(25.dp))
                .background(Color.White, RoundedCornerShape(25.dp))
                .clickable { expanded = true }
                .padding(horizontal = 28.dp, vertical = 15.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth(),

            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.weight(1f)
                ){
                    Image(
                        painter = painterResource(id = selectedCountry.flagResId),
                        contentDescription = "Flag of ${selectedCountry.name}",
                        modifier = Modifier
                            .width(44.dp)
                            .height(33.dp)
                    )
//                AsyncImage(
//                    model = ImageRequest.Builder(LocalContext.current)
//                        .data(selectedCountry.flagUrl)
//                        .crossfade(true)
//                        .build(),
//                    modifier = Modifier
//                        .width(44.dp)
//                        .height(33.dp),
//                    contentDescription = "Country Flag"
//                )
                    Spacer(modifier = Modifier.width(15.dp))
                    Text(
                        text = selectedCountry.name,
                        fontSize = 16.sp,
                        color = mainText
                    )
                }

                Image(
                    painter = painterResource(id = R.drawable.arrow_drop_down),
                    contentDescription = "arrow_drop_down",
                    modifier = Modifier.size(24.dp)
                )
            }
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .width(with(LocalDensity.current) { dropdownWidth.toDp() }) // 정확히 맞춤
                .background(Color.White),
        ) {
            val sortedList = remember(countryList) { countryList.sortedBy { it.name } }

            sortedList.forEach { country ->
                DropdownMenuItem(
                    onClick = {
                        onCountrySelected(country)
                        expanded = false
                    },
                    text = {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(color = Color.White)
                                .padding(horizontal = 18.dp, vertical = 15.dp),
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Image(
                                painter = painterResource(id = country.flagResId),
                                contentDescription = "Flag of ${country.name}",
                                modifier = Modifier
                                    .width(44.dp)
                                    .height(33.dp)
                            )
                            Spacer(modifier = Modifier.width(15.dp))
                            Text(
                                text = country.name,
                                fontSize = 16.sp,
                                color = mainText
                            )
                        }
                    }
                )
            }
        }
    }
}

