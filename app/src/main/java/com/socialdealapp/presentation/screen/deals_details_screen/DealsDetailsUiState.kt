package com.socialdealapp.presentation.screen.deals_details_screen

import com.socialdealapp.domain.currency.model.CurrencySymbol
import com.socialdealapp.domain.dealDescriptionDetails.model.DealItemDescriptionData
import com.socialdealapp.domain.dealDescriptionDetails.model.DealsDetailsModel

data class DealsDetailsUiState(
    val isLoading: Boolean = false,
    val dealsDetailsData: DealsDetailsModel? = null,
    val dealItemDescriptionData: DealItemDescriptionData? = null,
    val symbol: CurrencySymbol = CurrencySymbol.EURO,
)