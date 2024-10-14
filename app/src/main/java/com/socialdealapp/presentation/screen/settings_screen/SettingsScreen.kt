package com.socialdealapp.presentation.screen.settings_screen

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.socialdealapp.presentation.components.CurrencyToggle

@Composable
fun SettingsScreen(
    onBackPressed: () -> Unit,
    viewModel: SettingsScreenViewModel = hiltViewModel()
) {
    BackHandler {
        onBackPressed()
    }

    val state by viewModel.currencySymbol.collectAsState()

    Box(modifier = Modifier.fillMaxSize()) {
        CurrencyToggle(
            modifier = Modifier.align(Alignment.Center),
            isChecked = state.isSwitchChecked,
            currencySymbol = state.symbol,
            onCheckChanged = { isChecked -> viewModel.saveCurrencySymbol(isChecked) }
        )
    }
}