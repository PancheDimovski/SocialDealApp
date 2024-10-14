package com.socialdealapp.presentation.screen.deals_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.socialdealapp.domain.currency.use_case.GetCurrencySymbolUseCase
import com.socialdealapp.domain.deals.use_case.GetDealsUseCase
import com.socialdealapp.domain.favorite.use_case.GetFavoriteUseCase
import com.socialdealapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DealsScreenViewModel @Inject constructor(
    getDealsUseCase: GetDealsUseCase,
    getCurrencySymbolUseCase: GetCurrencySymbolUseCase,
    private val getFavoriteUseCase: GetFavoriteUseCase,
) : ViewModel() {

    val dealsUiState: StateFlow<DealsUiState> = combine(
        getDealsUseCase.getDealsData(),
        getFavoriteUseCase.getFavoriteDealsData(),
        getCurrencySymbolUseCase.getCurrencySymbol(),
    ) { deals, favorites, symbol ->
        when (deals) {
            is Resource.Success -> {
                DealsUiState.Content(
                    dealsData = deals.data?.map { deal ->
                        deal.copy(isAddedToFavorite = favorites.find { it.uniqueId == deal.uniqueId } != null)
                    }.orEmpty(),
                    currencySymbol = symbol.symbol,
                )
            }

            is Resource.Failure -> {
                DealsUiState.Failure(deals.error.toString())
            }

            is Resource.Loading -> {
                DealsUiState.Loading
            }

        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000L), DealsUiState.Loading)

    fun addToFavorite(id: String, addToFavorite: Boolean) {
        viewModelScope.launch {
            getFavoriteUseCase.updateFavoriteDeal(id, addToFavorite)
        }
    }

}