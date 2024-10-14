package com.socialdealapp.data.deals.di

import com.socialdealapp.data.db.SocialDealDatabase
import com.socialdealapp.data.deals.remote.DealsApiService
import com.socialdealapp.data.deals.repository.DealsRepositoryImpl
import com.socialdealapp.domain.deals.repository.DealsRepository
import com.socialdealapp.domain.deals.use_case.GetDealsUseCase
import com.socialdealapp.domain.deals.use_case.GetDealsUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SocialDealsModule {

    @Provides
    @Singleton
    fun provideDealsApiService(retrofit: Retrofit): DealsApiService {
        return retrofit.create(DealsApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideDealsRepository(dealsApiService: DealsApiService, db: SocialDealDatabase): DealsRepository {
        return DealsRepositoryImpl(dealsApiService, db.dealsDao)
    }

    @Provides
    @Singleton
    fun provideGetDealsUseCase(dealsRepository: DealsRepository): GetDealsUseCase {
        return GetDealsUseCaseImpl(dealsRepository)
    }
}
