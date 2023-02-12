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

@Composable
fun BannerTop(
    modifier: Modifier = Modifier,
    article: Article,
    navigateToDetail: (String, String) -> Unit
) {
    val date = DateFormatter.formatDate(article.publishedAt)
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(color = Color.White)
            .height(350.dp), verticalArrangement = Arrangement.SpaceAround
    ) {
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
        Row(modifier = modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text(text = article.author, color = Color.Gray.copy(0.9f))
            Text(text = date.toString(), color = Color.Gray.copy(0.9f))
        }
    }
}