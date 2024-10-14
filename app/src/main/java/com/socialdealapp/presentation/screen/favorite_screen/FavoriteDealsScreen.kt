package com.socialdealapp.presentation.screen.favorite_screen

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import com.socialdealapp.domain.deals.model.DealsModel
import com.socialdealapp.presentation.components.DealCardItem

@Composable
fun FavoriteDealsScreen(
    onBackPressed: () -> Unit,
    viewModel: FavoriteDealScreenViewModel = hiltViewModel(),
) {
    val uiState by viewModel.state.collectAsState()
    BackHandler {
        onBackPressed()
    }
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        item {
            if (uiState.dealsData.isEmpty()) {
                EmptyFavoritesMessage()
            }
        }
        items(uiState.dealsData.size) { i ->
            val dealsData = uiState.dealsData[i]
            DealFavoriteCard(data = dealsData, viewModel::addToFavorite, uiState.currency.symbol)
        }
    }
}

@Composable
private fun DealFavoriteCard(
    data: DealsModel,
    onToggleFavorite: (String, Boolean) -> Unit,
    currencySymbol: String,
    modifier: Modifier = Modifier,
) {
    DealCardItem(
        modifier = modifier,
        data = data,
        onToggleFavorite = onToggleFavorite,
        currency = currencySymbol
    )
}

@Composable
private fun EmptyFavoritesMessage() {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth(),
            text = "No favorite deals found.",
            textAlign = TextAlign.Center,
            color = Color.Black,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}