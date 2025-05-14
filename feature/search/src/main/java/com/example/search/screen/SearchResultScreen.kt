package com.example.search.screen

import android.util.Log
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage
import com.example.search.R
import com.example.search.SearchViewModel
import androidx.compose.runtime.LaunchedEffect

@Composable
fun SearchResultScreen(viewModel: SearchViewModel) {
    val scrollState = rememberScrollState()
//    val searchKeyword = "Paddington Bear" // 예시로 고정된 키워드
    val searchKeyword = viewModel.searchQuery.value
    val country = viewModel.country.value
    val guide by viewModel.guide.collectAsState()

    val backDispatcher = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher

    LaunchedEffect(searchKeyword) {
        if (searchKeyword.isNotBlank()) {
            Log.d("SearchResultScreen", "Calling fetchGuide() with $searchKeyword")
//            viewModel.fetchGuide(itemName = searchKeyword, country = country)
            viewModel.fetchGuide(searchKeyword, country)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFCFAF4))
            .padding(bottom = 20.dp)
            .verticalScroll(scrollState)
    ) {
        // 검색창 + 뒤로가기
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 30.dp, start = 16.dp, end = 36.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_back),
                contentDescription = "Back",
                modifier = Modifier
                    .size(24.dp)
                    .clickable { backDispatcher?.onBackPressed() }
            )

            Spacer(modifier = Modifier.width(10.dp))

            Box(
                contentAlignment = Alignment.CenterStart,
                modifier = Modifier
                    .weight(1f)
                    .height(44.dp)
                    .border(2.dp, Color(0xFF00611A), RoundedCornerShape(20.dp))
                    .background(Color(0xFFFFFFFF), RoundedCornerShape(20.dp))
                    .padding(horizontal = 24.dp)
            ) {
                Text(
                    text = searchKeyword,
                    fontSize = 15.sp,
                    color = Color.Black
                )
            }
        }

        Spacer(modifier = Modifier.height(30.dp))

        guide?.let {
            // 이미지
            AsyncImage(
                model = it.image,
                contentDescription = "${it.itemName} image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 39.dp)
            )

            Spacer(modifier = Modifier.height(10.dp))

            // 결과 텍스트
            Text(
                text = it.content,
                modifier = Modifier.padding(horizontal = 26.dp),
                fontSize = 16.sp,
                color = Color(0xFF4B4B4B)
            )
        } ?: run {
            Text(
                text = "Loading guide...",
                modifier = Modifier.padding(horizontal = 26.dp),
                fontSize = 16.sp,
                color = Color.Gray
            )
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun SearchResultScreenPreview() {
//    val fakeViewModel = SearchViewModel().apply {
//        updateSearchQuery("Paddington Bear")
//    }
//    SearchResultScreen(viewModel = fakeViewModel)
//}