package com.ahmadarwani.newsapps.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.ahmadarwani.newsapps.R
import com.ahmadarwani.newsapps.data.network.response.topheadlines.Article
import com.ahmadarwani.newsapps.utils.DateFormatter
import com.ahmadarwani.newsapps.utils.shimmerEffect

@Composable
fun BannerTop(
    modifier: Modifier = Modifier,
    article: Article,
    navigateToDetail: (String, String) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(color = Color.White)
            .height(350.dp), verticalArrangement = Arrangement.SpaceAround
    ) {
        val date = DateFormatter.formatDate(article.publishedAt)
        Text(text = "Berita Terkini", color = Color.Gray)
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current).data(article.urlToImage)
                .crossfade(true).build(),
            contentDescription = article.title,
            contentScale = ContentScale.Crop,
            placeholder = painterResource(id = R.drawable.ic_image_placeholder),
            modifier = modifier
                .clip(RoundedCornerShape(16.dp))
                .size(width = 350.dp, height = 200.dp)
                .clickable {
                    navigateToDetail(article.title, article.url)
                }
        )
        Text(
            text = article.title,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.h6,
            maxLines = 2,
            fontSize = 16.sp
        )
        Row(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = article.author, color = Color.Gray.copy(0.9f))
            Text(text = date.toString(), color = Color.Gray.copy(0.9f))
        }
    }
}

@Composable
fun BannerLoading(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(color = Color.White)
            .height(350.dp), verticalArrangement = Arrangement.SpaceAround
    ) {
        Box(
            modifier = modifier
                .fillMaxWidth(0.7f)
                .height(20.dp)
                .shimmerEffect()
        )
        Box(
            modifier = modifier
                .clip(RoundedCornerShape(16.dp))
                .size(width = 350.dp, height = 200.dp)
                .shimmerEffect()
        )
        Box(
            modifier = modifier
                .fillMaxWidth()
                .height(20.dp)
                .shimmerEffect()
        )
        Row(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Box(
                modifier = modifier
                    .fillMaxWidth(0.2f)
                    .height(20.dp)
                    .shimmerEffect()
            )
            Box(
                modifier = modifier
                    .fillMaxWidth(0.2f)
                    .height(20.dp)
                    .shimmerEffect()
            )
        }
    }
}