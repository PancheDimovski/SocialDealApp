package com.socialdealapp.data.deals.repository

import com.socialdealapp.data.db.dao.DealsDao
import com.socialdealapp.data.db.entity.DealsEntity
import com.socialdealapp.data.deals.remote.DealsApiService
import com.socialdealapp.domain.deals.model.DealsModel
import com.socialdealapp.domain.deals.repository.DealsRepository
import com.socialdealapp.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import retrofit2.HttpException
import timber.log.Timber
import java.io.IOException

class DealsRepositoryImpl(
    private val dealsApiService: DealsApiService,
    private val dealsDao: DealsDao
) : DealsRepository {

    override fun getDealsList(): Flow<Resource<List<DealsModel>>> = flow {
        emit(Resource.Loading())
        try {
            val dealsData = dealsApiService.getDealsData()

            dealsDao.insertDeals(deals = dealsData.deals.map {

                val dealDbData = dealsDao.getDealByUniqueId(it.unique)
                val isAddedToFavorite = dealDbData.favoriteDeal ?: false

                DealsEntity(
                    uniqueId = it.unique,
                    title = it.title,
                    imageUrl = it.image,
                    soldLabel = it.soldLabel,
                    companyName = it.company,
                    city = it.city,
                    originalPrice = it.prices.price?.amount,
                    fromPrice = it.prices.fromPrice?.amount,
                    symbol = it.prices.price?.currency?.symbol,
                    currencyCode = it.prices.price?.currency?.code,
                    discountLabel = it.prices.discountLabel,
                    favoriteDeal = isAddedToFavorite
                )
            })
        } catch (exception: HttpException) {
            emit(
                Resource.Failure(
                    error = exception.message().toString()
                )
            )
        } catch (exception: IOException) {
            emit(
                Resource.Failure(
                    error = exception.message.toString()
                )
            )
        }

        val newDealsData = dealsDao.getAllDeals().map {

            val dealId = dealsDao.getDealByUniqueId(it.uniqueId)
            val isAddedToFavorite = dealId?.favoriteDeal ?: false

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
                isAddedToFavorite = isAddedToFavorite
            )
        }
        emit(Resource.Success(data = newDealsData))

    }
}