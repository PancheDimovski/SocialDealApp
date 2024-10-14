package com.socialdealapp.presentation.screen.deals_screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.socialdealapp.domain.deals.model.DealsModel
import com.socialdealapp.presentation.components.DealCardItem

@Composable
fun DealsRouter(
    viewModel: DealsScreenViewModel = hiltViewModel(),
    onNavigate: (String) -> Unit
) {
    val uiState by viewModel.dealsUiState.collectAsState()
    DealsScreen(uiState = uiState, onNavigate = onNavigate, onAddToFavorite = viewModel::addToFavorite)
}

@Composable
fun DealsScreen(
    uiState: DealsUiState,
    onAddToFavorite: (String, Boolean) -> Unit,
    onNavigate: (String) -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {

        when (uiState) {
            is DealsUiState.Loading -> {
                Box(modifier = Modifier.fillMaxSize()) {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
            }

            is DealsUiState.Content -> {
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    uiState.dealsData.let { dealsData ->
                        items(dealsData.size) { index ->
                            DealCard(
                                data = dealsData[index],
                                onNavigate = onNavigate,
                                onToggleFavorite = onAddToFavorite,
                                currencySymbol = uiState.currencySymbol,
                            )
                        }
                    }
                }
            }

            is DealsUiState.Failure -> {


            }
        }
    }
}

@Composable
fun DealCard(
    data: DealsModel,
    onNavigate: (String) -> Unit,
    onToggleFavorite: (String, Boolean) -> Unit,
    currencySymbol: String
) {
    DealCardItem(
        data = data,
        onNavigate = onNavigate,
        onToggleFavorite = onToggleFavorite,
        currency = currencySymbol
    )
}

@Preview
@Composable
private fun DealsScreenLoadingPreview() {
    DealsScreen(
        uiState = DealsUiState.Loading,
        onAddToFavorite = { _, _ -> }
    ) { }
}