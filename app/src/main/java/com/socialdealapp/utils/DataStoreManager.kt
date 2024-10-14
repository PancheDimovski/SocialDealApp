package com.socialdealapp.utils

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.socialdealapp.domain.currency.model.CurrencySymbol
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore by preferencesDataStore(name = "app_preferences")

class DataStoreManager(private val context: Context) {

    private val currencySymbolKey = stringPreferencesKey("settings_currency_symbol")

    suspend fun saveCurrencySymbol(value: String) {
        context.dataStore.edit { preferences ->
            preferences[currencySymbolKey] = value
        }
    }

    val getCurrencySymbol: Flow<CurrencySymbol> = context.dataStore.data
        .map { preferences ->
            when(preferences[currencySymbolKey]) {
                CurrencySymbol.DOLLAR.symbol -> CurrencySymbol.DOLLAR
                else -> CurrencySymbol.EURO
            }
        }
}