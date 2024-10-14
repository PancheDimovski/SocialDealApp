package com.socialdealapp.domain.currency.use_case

import com.socialdealapp.domain.currency.model.CurrencySymbol
import kotlinx.coroutines.flow.Flow

interface GetCurrencySymbolUseCase {

    fun getCurrencySymbol (): Flow<CurrencySymbol>
}