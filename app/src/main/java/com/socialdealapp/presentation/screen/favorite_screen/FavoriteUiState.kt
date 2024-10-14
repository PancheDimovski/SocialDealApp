package com.socialdealapp.presentation.screen.favorite_screen

import com.socialdealapp.domain.currency.model.CurrencySymbol
import com.socialdealapp.domain.deals.model.DealsModel

data class FavoriteUiState  (
    val dealsData: List<DealsModel>,
    val currency: CurrencySymbol,
)