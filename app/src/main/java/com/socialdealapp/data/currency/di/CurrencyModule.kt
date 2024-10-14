package com.socialdealapp.data.currency.di

import com.socialdealapp.data.currency.repository.CurrencyRepository
import com.socialdealapp.data.currency.repository.CurrencyRepositoryImpl
import com.socialdealapp.domain.currency.use_case.GetCurrencySymbolUseCase
import com.socialdealapp.domain.currency.use_case.GetCurrencySymbolUseCaseImpl
import com.socialdealapp.utils.DataStoreManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CurrencyModule {

    @Provides
    @Singleton
    fun provideCurrencyRepository(dataStoreManager: DataStoreManager): CurrencyRepository {
        return CurrencyRepositoryImpl(dataStoreManager)
    }

    @Provides
    @Singleton
    fun provideGetCurrencyUseCase(currencyRepository: CurrencyRepository): GetCurrencySymbolUseCase {
        return GetCurrencySymbolUseCaseImpl(currencyRepository)
    }


}