package com.socialdealapp.domain.favorite.repository

import com.socialdealapp.domain.deals.model.DealsModel
import kotlinx.coroutines.flow.Flow

interface FavoriteRepository {

    suspend fun updateFavoriteDeal(id: String, addToFavorite: Boolean)

    fun getFavoriteDealsData(): Flow<List<DealsModel>>
}