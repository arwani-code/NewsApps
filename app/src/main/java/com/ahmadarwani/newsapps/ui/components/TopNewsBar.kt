package com.ahmadarwani.newsapps.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ahmadarwani.newsapps.R.*
import com.ahmadarwani.newsapps.ui.theme.NewsAppsTheme


@Composable
fun TopNewsBar(
    modifier: Modifier = Modifier,
    canNavigate: Boolean = false,
    title: String,
    navigateHandler: () -> Unit = {}
) {
    Column(modifier = modifier.background(Color.White)) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .height(55.dp)
                .padding(start = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            if (canNavigate) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "back button",
                    modifier = modifier
                        .padding(end = 12.dp)
                        .clickable { navigateHandler() },
                    tint = Color.Gray
                )
            } else {
                Image(
                    imageVector = ImageVector.vectorResource(id = drawable.ic_mandiri),
                    contentDescription = "Icon",
                    modifier = modifier
                        .size(60.dp)
                        .padding(end = 16.dp)
                        .clip(RoundedCornerShape(10.dp)),
                    contentScale = ContentScale.Fit,
                )
            }
            Text(
                text = title,
                style = MaterialTheme.typography.h5,
                color = Color.Gray,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
        Divider(modifier = modifier.padding(top = 8.dp))
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    NewsAppsTheme {
        TopNewsBar(title = "Mandiri News")
    }
}