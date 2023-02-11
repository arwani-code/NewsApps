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
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ahmadarwani.newsapps.ui.home.HomeScreen
import com.ahmadarwani.newsapps.ui.navigations.Screen
import com.ahmadarwani.newsapps.ui.theme.NewsAppsTheme
import com.ahmadarwani.newsapps.ui.theme.White50

@Composable
fun JetNewsApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    Scaffold(topBar = { TopNewsBar() }) { innerPadding ->
        NavHost(
            navController = navController,
            modifier = modifier.padding(innerPadding),
            startDestination = Screen.Home.route
        ) {
            composable(Screen.Home.route) {
                HomeScreen()
            }
        }
    }
}

@Composable
fun TopNewsBar(
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.background(Color.White)) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .height(55.dp)
                .padding(start = 20.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_mandiri),
                contentDescription = "Icon",
                modifier = modifier
                    .size(60.dp)
                    .padding(end = 16.dp)
                    .clip(RoundedCornerShape(10.dp)),
                contentScale = ContentScale.Fit,
            )
            Text(
                text = "Mandiri News",
                style = MaterialTheme.typography.h5,
                color = Color.Gray,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
        }
        Divider(modifier = modifier.padding(top = 8.dp))
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    NewsAppsTheme {
        TopNewsBar()
    }
}