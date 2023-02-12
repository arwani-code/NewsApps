package com.ahmadarwani.newsapps.ui.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.ahmadarwani.newsapps.R
import com.ahmadarwani.newsapps.data.network.response.everything.Article
import com.ahmadarwani.newsapps.utils.DateFormatter

@Composable
fun NewsColumnItem(
    modifier: Modifier = Modifier,
    article: Article,
    navigateToDetail: (String, String) -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(115.dp)
            .clickable {
                navigateToDetail(article.title, article.url)
            }
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current).data(article.urlToImage)
                .crossfade(true).build(),
            contentDescription = article.title,
            contentScale = ContentScale.Crop,
            placeholder = painterResource(id = R.drawable.ic_image_placeholder),
            modifier = modifier
                .clip(RoundedCornerShape(16.dp))
                .size(100.dp)
        )
        Column(
            modifier = modifier
                .padding(top = 4.dp, start = 8.dp, bottom = 16.dp)
                .fillMaxHeight(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = article.title,
                style = MaterialTheme.typography.h5,
                fontSize = 16.sp,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis
            )
            Row(
                modifier = modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Bottom
            ) {
                SectionTitle(title = article.author)
                SectionDate(publishedAt = article.publishedAt)
            }
        }
    }
}

@Composable
fun SectionTitle(modifier: Modifier = Modifier, title: String) {
    Row(modifier = modifier, verticalAlignment = Alignment.Bottom) {
        Icon(
            imageVector = Icons.Outlined.Person,
            contentDescription = "person",
            modifier = modifier
                .padding(end = 4.dp)
                .size(20.dp),
            tint = Color.Gray
        )
        Text(text = title, color = Color.Gray, fontSize = 12.sp)
    }
}


@Composable
fun SectionDate(modifier: Modifier = Modifier, publishedAt: String) {
    val date = DateFormatter.formatDate(publishedAt)
    Row(modifier = modifier, verticalAlignment = Alignment.Bottom) {
        Icon(
            imageVector = Icons.Outlined.DateRange,
            contentDescription = "date range",
            modifier = modifier
                .padding(end = 4.dp)
                .size(20.dp),
            tint = Color.Gray
        )
        Text(text = date.toString(), color = Color.Gray, fontSize = 12.sp)
    }
}