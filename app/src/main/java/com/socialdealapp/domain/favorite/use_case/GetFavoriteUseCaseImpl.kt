package com.socialdealapp.domain.favorite.use_case

import com.socialdealapp.domain.deals.model.DealsModel
import com.socialdealapp.domain.favorite.repository.FavoriteRepository
import kotlinx.coroutines.flow.Flow

class GetFavoriteUseCaseImpl(private val repository: FavoriteRepository): GetFavoriteUseCase {

    override suspend fun updateFavoriteDeal(id: String, addToFavorite: Boolean) {
        return repository.updateFavoriteDeal(id, addToFavorite)
    }

    override fun getFavoriteDealsData(): Flow<List<DealsModel>> {
        return repository.getFavoriteDealsData()
    }
}