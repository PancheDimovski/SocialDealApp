package com.socialdealapp.presentation.screen.deals_screen

import com.socialdealapp.domain.deals.model.DealsModel

sealed class DealsUiState {

    data class Content(
        val dealsData: List<DealsModel>,
        val currencySymbol: String
    ) : DealsUiState()
    data object Loading: DealsUiState()
    data class Failure(val exception: String): DealsUiState()
}