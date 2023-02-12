package com.ahmadarwani.newsapps.ui.home

import android.graphics.Paint.Align
import android.text.Layout.Alignment
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ahmadarwani.newsapps.data.network.response.everything.Everything
import com.ahmadarwani.newsapps.data.network.response.topheadlines.TopHeadlines
import com.ahmadarwani.newsapps.ui.components.TopNewsBar
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    homeViewModel: HomeViewModel = hiltViewModel(),
    navigateToDetail: (String, String) -> Unit
) {
    val everything by homeViewModel.uiStateEverything.collectAsState()
    val topHeadline by homeViewModel.uiStateTopHeadline.collectAsState()

    var isRefreshing by remember {
        mutableStateOf(false)
    }
    val stateRefresh = rememberSwipeRefreshState(isRefreshing = isRefreshing)
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            TopNewsBar(title = "Mandiri News")
        }
    ) { paddingValues ->
        when (everything) {
            is UiState.Loading -> {
                LazyColumn(
                    modifier = modifier.padding(paddingValues),
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    item {
                        BannerLoading()
                    }
                    items(5) {
                        NewsColumnLoading()
                    }
                }
                homeViewModel.getEverything()
            }
            is UiState.Success -> {
                val articleEverything = (everything as UiState.Success<Everything>).data.articles
                SwipeRefresh(state = stateRefresh, onRefresh = {
                    coroutineScope.launch {
                        isRefreshing = !isRefreshing
                        delay(300L)
                        isRefreshing = !isRefreshing
                    }
                }) {
                    LazyColumn(
                        modifier = modifier.padding(paddingValues),
                        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
                    ) {
                        item {
                            when (topHeadline) {
                                is UiState.Loading -> {
                                    BannerLoading()
                                    homeViewModel.getTopHeadline()
                                }
                                is UiState.Success -> {
                                    val data =
                                        (topHeadline as UiState.Success<TopHeadlines>).data.articles
                                    BannerTop(
                                        article = data[0],
                                        navigateToDetail = navigateToDetail
                                    )
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
                        items(articleEverything, key = { it.title }) { article ->
                            NewsColumnItem(
                                article = article,
                                navigateToDetail = navigateToDetail
                            )
                        }
                    }
                }
            }
            is UiState.Error -> {}
        }
    }
}