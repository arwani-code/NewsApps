package com.ahmadarwani.newsapps.ui.home

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.ahmadarwani.newsapps.data.network.response.everything.Everything

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    val everything by homeViewModel.uiStateEverything.collectAsState()
    val topHeadline by homeViewModel.uiStateTopHeadline.collectAsState()

    when(everything){
        is UiState.Loading -> homeViewModel.getEverything()
        is UiState.Success -> Log.i("SUCCESS", "HomeScreen: ${(everything as UiState.Success<Everything>).data.articles.size}")
        is UiState.Error -> Log.i("ERROR", "HomeScreen: ${(everything as UiState.Error).errorMessage}")
    }
}