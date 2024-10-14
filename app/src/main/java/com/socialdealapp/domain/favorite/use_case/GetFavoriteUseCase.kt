package com.socialdealapp.domain.favorite.use_case

import com.socialdealapp.domain.deals.model.DealsModel
import kotlinx.coroutines.flow.Flow

interface GetFavoriteUseCase {

    suspend fun updateFavoriteDeal(id: String, addToFavorite: Boolean)

    fun getFavoriteDealsData(): Flow<List<DealsModel>>
}