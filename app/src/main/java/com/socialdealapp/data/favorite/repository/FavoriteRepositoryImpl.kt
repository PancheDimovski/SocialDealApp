package com.socialdealapp.data.favorite.repository

import com.socialdealapp.data.db.dao.DealsDao
import com.socialdealapp.domain.deals.model.DealsModel
import com.socialdealapp.domain.favorite.repository.FavoriteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class FavoriteRepositoryImpl(private val dealsDao: DealsDao): FavoriteRepository {

    override suspend fun updateFavoriteDeal(id: String, addToFavorite: Boolean) {
        val dealsDbData = dealsDao.getDealByUniqueId(id)
        val updatedDeal = dealsDbData.copy(favoriteDeal = addToFavorite)

        dealsDao.addDealToFavorite(updatedDeal)
    }

    override fun getFavoriteDealsData(): Flow<List<DealsModel>> =
        dealsDao.getFavoriteDeals().map {  list ->
            list.map {
                DealsModel(
                    uniqueId = it.uniqueId,
                    title = it.title,
                    imageUrl = it.imageUrl,
                    soldLabel = it.soldLabel,
                    companyName = it.companyName,
                    city = it.city,
                    originalPrice = it.originalPrice,
                    fromPrice = it.fromPrice,
                    symbol = it.symbol,
                    currencyCode = it.currencyCode,
                    discountLabel = it.discountLabel,
                    isAddedToFavorite = it.favoriteDeal ?: false,
                )
            }
        }
}