package com.socialdealapp.presentation.screen.deals_details_screen

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import com.socialdealapp.domain.dealDescriptionDetails.model.DealItemDescriptionData
import com.socialdealapp.presentation.components.DealCardDetailsItem
import com.socialdealapp.presentation.components.DealDetailsItem
import com.socialdealapp.utils.Dimensions


@Composable
fun DealsDetailsScreen(
    dealId: String?,
    onBackPressed: () -> Unit,
    viewModel: DealsDetailsScreenViewModel = hiltViewModel()
) {
    val uiState by viewModel.dealsDetailsUiState.collectAsState()

    LaunchedEffect(dealId) {
        dealId?.let {
            viewModel.checkDealById(dealId = dealId)
        }
    }
    BackHandler {
        onBackPressed()
    }
    uiState.dealItemDescriptionData?.let { DealsItemDetail(it, uiState.symbol.symbol) }
}

@Composable
private fun DealsItemDetail(
    data: DealItemDescriptionData,
    currencySymbol: String,
    modifier: Modifier = Modifier,
) {
    Surface(color = Color.White) {
        Column(
            modifier
                .padding(Dimensions.dp12)
                .verticalScroll(rememberScrollState())
        ) {
            // Deal Section
            DealCardDetailsItem(data, currencySymbol)
            // Description Section
            DealDetailsItem(data, currencySymbol)
        }
    }
}

