package com.socialdealapp.presentation.screen.favorite_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.socialdealapp.domain.currency.model.CurrencySymbol
import com.socialdealapp.domain.currency.use_case.GetCurrencySymbolUseCase
import com.socialdealapp.domain.favorite.use_case.GetFavoriteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteDealScreenViewModel @Inject constructor(
    private val getFavoriteUseCase: GetFavoriteUseCase,
    getCurrencySymbolUseCase: GetCurrencySymbolUseCase,
) : ViewModel() {

    val state: StateFlow<FavoriteUiState> = getFavoriteUseCase.getFavoriteDealsData().combine(
        getCurrencySymbolUseCase.getCurrencySymbol()
    ) { favDeals, currency ->
        FavoriteUiState(favDeals, currency)
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000L),
        FavoriteUiState(emptyList(), CurrencySymbol.EURO)
    )

    fun addToFavorite(id: String, shouldAddToFavorite: Boolean) {
        viewModelScope.launch {
            getFavoriteUseCase.updateFavoriteDeal(id, shouldAddToFavorite)
        }
    }
}