package com.socialdealapp.presentation.components

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun FavoriteButton(
    addDealToFavorite: (uniqueId: String, addToFavorite: Boolean) -> Unit,
    uniqueId: String,
    isFavorite: Boolean,
    modifier: Modifier = Modifier,
) {
    IconButton(
        modifier = modifier,
        onClick = {
            val addToFavorite = !isFavorite
            addDealToFavorite(uniqueId, addToFavorite)
        }
    ) {
        Icon(
            imageVector = if (isFavorite) Icons.Filled.Favorite else Icons.Default.FavoriteBorder,
            contentDescription = "Toggle favorite",
            tint = Color.White
        )
    }
}