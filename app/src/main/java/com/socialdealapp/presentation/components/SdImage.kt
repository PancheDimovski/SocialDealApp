package com.socialdealapp.presentation.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun SdImage(
    imageUrl: String,
    modifier: Modifier = Modifier,
    contentDescription: String? = null,
    contentScale: ContentScale = ContentScale.FillBounds,
) {
    val prefixUrl = "https://images.socialdeal.nl"
    val fullImageUrl = prefixUrl + imageUrl
    AsyncImage(
        model = fullImageUrl,
        contentDescription = contentDescription,
        contentScale = contentScale,
        modifier = modifier
            .fillMaxSize()
            .clip(RoundedCornerShape(8.dp))
    )
}