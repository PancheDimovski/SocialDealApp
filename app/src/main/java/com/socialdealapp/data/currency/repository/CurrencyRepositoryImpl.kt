package com.socialdealapp.data.currency.repository

import com.socialdealapp.domain.currency.model.CurrencySymbol
import com.socialdealapp.utils.DataStoreManager
import kotlinx.coroutines.flow.Flow

interface CurrencyRepository {
    fun getCurrencySymbol(): Flow<CurrencySymbol>
}

internal class CurrencyRepositoryImpl(private val dataStoreManager: DataStoreManager) : CurrencyRepository {

    override fun getCurrencySymbol(): Flow<CurrencySymbol> {
        return dataStoreManager.getCurrencySymbol
    }
}