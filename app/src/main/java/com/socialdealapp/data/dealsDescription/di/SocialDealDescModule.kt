package com.socialdealapp.data.dealsDescription.di

import com.socialdealapp.data.db.SocialDealDatabase
import com.socialdealapp.data.dealsDescription.remote.DealsDescriptionApi
import com.socialdealapp.data.dealsDescription.repository.DealsDescriptionRepositoryImpl
import com.socialdealapp.domain.dealDescriptionDetails.repository.DealsDescriptionDetailsRepository
import com.socialdealapp.domain.dealDescriptionDetails.use_case.GetDealsDescriptionUseCase
import com.socialdealapp.domain.dealDescriptionDetails.use_case.GetDealsDescriptionUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SocialDealDescModule {

    @Provides
    @Singleton
    fun provideDealsDescApiService(retrofit: Retrofit): DealsDescriptionApi {
        return retrofit.create(DealsDescriptionApi::class.java)
    }

    @Provides
    @Singleton
    fun provideDealsDescRepository(
        dealsDescApiService: DealsDescriptionApi,
        db: SocialDealDatabase
    ): DealsDescriptionDetailsRepository {
        return DealsDescriptionRepositoryImpl(dealsDescApiService, db.dealsDao)
    }

    @Provides
    @Singleton
    fun provideGetDealsDescUseCase(dealsDescRepository: DealsDescriptionDetailsRepository): GetDealsDescriptionUseCase {
        return GetDealsDescriptionUseCaseImpl(dealsDescRepository)
    }
}