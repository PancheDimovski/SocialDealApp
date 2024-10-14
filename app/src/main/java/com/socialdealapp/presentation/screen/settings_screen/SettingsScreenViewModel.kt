package com.socialdealapp.presentation.screen.settings_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.socialdealapp.domain.currency.model.CurrencySymbol
import com.socialdealapp.utils.DataStoreManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsScreenViewModel @Inject constructor(
    private val dataStoreManager: DataStoreManager
) : ViewModel() {

    val currencySymbol: StateFlow<SettingsUiState> = dataStoreManager.getCurrencySymbol.map {
        SettingsUiState(
            symbol = it,
            isSwitchChecked = it == CurrencySymbol.EURO
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000L), SettingsUiState())

    fun saveCurrencySymbol(isChecked: Boolean) {
        val newSymbol = if (isChecked) CurrencySymbol.EURO else CurrencySymbol.DOLLAR
        viewModelScope.launch {
            dataStoreManager.saveCurrencySymbol(newSymbol.symbol)
        }
    }

}