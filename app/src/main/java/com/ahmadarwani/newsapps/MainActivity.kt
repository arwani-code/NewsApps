package com.ahmadarwani.newsapps

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.ahmadarwani.newsapps.ui.theme.NewsAppsTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NewsAppsTheme {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    JetNewsApp()
                }
            }
        }
    }
}
