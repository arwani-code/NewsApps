package com.ahmadarwani.newsapps.ui.detail

import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.ahmadarwani.newsapps.ui.components.TopNewsBar

@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    title: String,
    url: String,
    navigateHandler: () -> Unit
) {
    Scaffold(modifier = modifier,
        topBar = {
            TopNewsBar(title = title, canNavigate = true, navigateHandler = navigateHandler)
        }
    ) { innerPadding ->
        Box(
            modifier = modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            AndroidView(factory = {
                WebView(it).apply {
                    layoutParams = ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT
                    )
                    webViewClient = WebViewClient()
                }
            }, update = {
                it.loadUrl(url)
            })
        }
    }
}