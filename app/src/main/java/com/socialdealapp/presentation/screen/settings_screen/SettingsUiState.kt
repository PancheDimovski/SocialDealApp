package com.socialdealapp.presentation.screen.settings_screen

import com.socialdealapp.domain.currency.model.CurrencySymbol

data class SettingsUiState (
    val symbol: CurrencySymbol = CurrencySymbol.EURO,
    val isSwitchChecked: Boolean = true,
)