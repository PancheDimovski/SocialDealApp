package com.socialdealapp.presentation.screen.deals_details_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.socialdealapp.domain.currency.use_case.GetCurrencySymbolUseCase
import com.socialdealapp.domain.currency.use_case.GetCurrencySymbolUseCaseImpl
import com.socialdealapp.domain.dealDescriptionDetails.model.DealItemDescriptionData
import com.socialdealapp.domain.dealDescriptionDetails.model.DealsDetailsModel
import com.socialdealapp.domain.dealDescriptionDetails.use_case.GetDealsDescriptionUseCase
import com.socialdealapp.domain.deals.model.DealsModel
import com.socialdealapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class DealsDetailsScreenViewModel @Inject constructor(
    private val getDealsDescriptionUseCase: GetDealsDescriptionUseCase,
    getCurrencySymbolUseCase: GetCurrencySymbolUseCase
) : ViewModel() {

    private val _dealsDetailsUiState = MutableStateFlow(DealsDetailsUiState())
    val dealsDetailsUiState: StateFlow<DealsDetailsUiState> = _dealsDetailsUiState.combine(
        getCurrencySymbolUseCase.getCurrencySymbol()
    ) { state, currency ->
        state.copy(symbol = currency)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000L), DealsDetailsUiState())

    fun checkDealById(dealId: String) = runBlocking {
        viewModelScope.launch {
            getDealsDescriptionUseCase.getDealsData(dealId)
                .distinctUntilChanged().collectLatest { result ->
                    when (result) {
                        is Resource.Success -> {
                            result.data?.let { fetchDetailsData(it) }
                        }

                        is Resource.Failure -> {
                            _dealsDetailsUiState.value = dealsDetailsUiState.value.copy(
                                isLoading = false
                            )
                        }

                        is Resource.Loading -> {
                            _dealsDetailsUiState.value = dealsDetailsUiState.value.copy(
                                isLoading = true
                            )
                        }
                    }
                }
        }
    }

    private fun fetchDetailsData(dealsData: DealsModel) {
        viewModelScope.launch {
            getDealsDescriptionUseCase.getDealsDescriptionData()
                .collectLatest { result ->
                    when (result) {
                        is Resource.Success -> {
                            val dealDetailData = result.data
                            if (dealDetailData != null) {
                                _dealsDetailsUiState.value =
                                    _dealsDetailsUiState.value.copy(
                                        dealItemDescriptionData = createDealItemDescriptionData(
                                            dealsData,
                                            dealDetailData
                                        ),
                                        isLoading = false
                                    )
                            }
                        }

                        is Resource.Failure -> {
                            _dealsDetailsUiState.value = dealsDetailsUiState.value.copy(
                                dealsDetailsData = result.data,
                                isLoading = false
                            )
                        }

                        is Resource.Loading -> {
                            _dealsDetailsUiState.value = dealsDetailsUiState.value.copy(
                                dealsDetailsData = result.data,
                                isLoading = true
                            )
                        }
                    }
                }
        }
    }

    private fun createDealItemDescriptionData(
        dealsData: DealsModel,
        dealDetailData: DealsDetailsModel
    ): DealItemDescriptionData {
        return DealItemDescriptionData(
            uniqueId = dealsData.uniqueId,
            title = dealsData.title,
            imageUrl = dealsData.imageUrl,
            soldLabel = dealsData.soldLabel,
            companyName = dealsData.companyName,
            city = dealsData.city,
            originalPrice = dealsData.originalPrice,
            fromPrice = dealsData.fromPrice,
            symbol = dealsData.symbol,
            currencyCode = dealsData.currencyCode,
            discountLabel = dealsData.discountLabel,
            descUniqueId = dealDetailData.uniqueId,
            descTitle = dealDetailData.title,
            descCompany = dealDetailData.company,
            description = dealDetailData.description,
            descCity = dealDetailData.city,
            descSoldLabel = dealDetailData.soldLabel,
            descPrice = dealDetailData.originalPrice,
            descCurrencySymbol = dealDetailData.currencySymbol,
            descCurrencyCode = dealDetailData.currencyCode,
            descFromPrice = dealDetailData.fromPrice,
            descPriceLabel = dealDetailData.priceLabel,
            descDiscountLabel = dealDetailData.discountLabel
        )
    }
}