package com.ahmadarwani.newsapps

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.ahmadarwani.newsapps.ui.detail.DetailScreen
import com.ahmadarwani.newsapps.ui.home.HomeScreen
import com.ahmadarwani.newsapps.ui.navigations.Screen
import com.ahmadarwani.newsapps.ui.theme.NewsAppsTheme

@Composable
fun JetNewsApp(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(Screen.Home.route) {
            HomeScreen(navigateToDetail = { title, url ->
                navController.currentBackStackEntry?.savedStateHandle?.set(key = "title", title)
                navController.currentBackStackEntry?.savedStateHandle?.set(key = "url", url)
                navController.navigate(Screen.Detail.route)
            })
        }
        composable(
            route = Screen.Detail.route
        ) {
            val title = navController.previousBackStackEntry?.savedStateHandle?.get<String>("title")
            val url = navController.previousBackStackEntry?.savedStateHandle?.get<String>("url")
            if (title != null && url != null) {
                DetailScreen(
                    title = title,
                    url = url,
                    navigateHandler = { navController.navigateUp() })
            }
        }
    }
}
