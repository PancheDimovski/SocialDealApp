package com.socialdealapp.domain.currency.use_case

import com.socialdealapp.data.currency.repository.CurrencyRepository
import com.socialdealapp.domain.currency.model.CurrencySymbol
import kotlinx.coroutines.flow.Flow

internal class GetCurrencySymbolUseCaseImpl(private val repository: CurrencyRepository) :
    GetCurrencySymbolUseCase {
    override fun getCurrencySymbol(): Flow<CurrencySymbol> {
        return repository.getCurrencySymbol()
    }
}