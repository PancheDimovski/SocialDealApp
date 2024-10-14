package com.socialdealapp.data.favorite.di

import com.socialdealapp.data.db.SocialDealDatabase
import com.socialdealapp.data.favorite.repository.FavoriteRepositoryImpl
import com.socialdealapp.domain.favorite.repository.FavoriteRepository
import com.socialdealapp.domain.favorite.use_case.GetFavoriteUseCase
import com.socialdealapp.domain.favorite.use_case.GetFavoriteUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FavoriteModule {


    @Provides
    @Singleton
    fun provideFavoriteRepository(db: SocialDealDatabase): FavoriteRepository {
        return FavoriteRepositoryImpl(db.dealsDao)
    }

    @Provides
    @Singleton
    fun provideFavoriteUseCase(favoriteRepository: FavoriteRepository): GetFavoriteUseCase {
        return GetFavoriteUseCaseImpl(favoriteRepository)
    }


}