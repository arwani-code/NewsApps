package com.ahmadarwani.newsapps.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ahmadarwani.newsapps.data.network.response.everything.Everything
import com.ahmadarwani.newsapps.data.network.response.topheadlines.TopHeadlines
import com.ahmadarwani.newsapps.ui.theme.White50

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    val everything by homeViewModel.uiStateEverything.collectAsState()
    val topHeadline by homeViewModel.uiStateTopHeadline.collectAsState()

    Scaffold(
        modifier = modifier
            .padding(horizontal = 16.dp)
    ) { paddingValues ->
        when (everything) {
            is UiState.Loading -> homeViewModel.getEverything()
            is UiState.Success -> {
                val articleEverything = (everything as UiState.Success<Everything>).data.articles
                LazyColumn(
                    modifier = modifier.padding(paddingValues),
                    contentPadding = PaddingValues(8.dp),
                ) {
                    item {
                        when (topHeadline) {
                            is UiState.Loading -> homeViewModel.getTopHeadline()
                            is UiState.Success -> {
                                val data =
                                    (topHeadline as UiState.Success<TopHeadlines>).data.articles
                                BannerTop(article = data[0])
                            }
                            is UiState.Error -> {}
                        }
                    }
                    item {
                        Text(
                            text = "Semua Berita",
                            style = MaterialTheme.typography.h5,
                            color = Color.Black.copy(alpha = 0.6f),
                            modifier = modifier.padding(vertical = 8.dp)
                        )
                    }
                    items(articleEverything) { article ->
                        NewsColumnItem(article = article)
                    }
                }
            }
            is UiState.Error -> {}
        }
    }
}