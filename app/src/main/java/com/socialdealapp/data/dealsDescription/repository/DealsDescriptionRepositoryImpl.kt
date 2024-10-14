package com.socialdealapp.data.dealsDescription.repository

import com.google.gson.Gson
import com.socialdealapp.data.db.dao.DealsDao
import com.socialdealapp.data.db.entity.DealsEntity
import com.socialdealapp.data.dealsDescription.remote.DealsDescriptionApi
import com.socialdealapp.data.dealsDescription.remote.DealsDescriptionData
import com.socialdealapp.domain.dealDescriptionDetails.model.DealsDetailsModel
import com.socialdealapp.domain.dealDescriptionDetails.repository.DealsDescriptionDetailsRepository
import com.socialdealapp.domain.deals.model.DealsModel
import com.socialdealapp.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.ResponseBody
import okio.Buffer
import retrofit2.HttpException
import timber.log.Timber
import java.io.IOException
import java.nio.charset.Charset

class DealsDescriptionRepositoryImpl(
    private var dealsDescriptionApi: DealsDescriptionApi,
    private var dealsDao: DealsDao
) : DealsDescriptionDetailsRepository {

    override suspend fun getDealsData(uniqueId: String): Flow<Resource<DealsModel>> = flow {

        try {
            val dealsDbData = dealsDao.getDealByUniqueId(uniqueId)
            if (dealsDbData != null) {
                val data = mapToModel(dealsDbData)
                emit(Resource.Success(data))
            }
        } catch (exception: IOException) {
            emit(
                Resource.Failure(
                    error = exception.message.toString()
                )
            )
        }
    }

    private fun mapToModel(dealsDbData: DealsEntity): DealsModel {
        val isAddedToFavorite = dealsDbData?.favoriteDeal ?: false
        val dealData = DealsModel(
            uniqueId = dealsDbData.uniqueId,
            title = dealsDbData.title,
            imageUrl = dealsDbData.imageUrl,
            soldLabel = dealsDbData.soldLabel,
            companyName = dealsDbData.companyName,
            city = dealsDbData.city,
            originalPrice = dealsDbData.originalPrice,
            fromPrice = dealsDbData.fromPrice,
            symbol = dealsDbData.symbol,
            currencyCode = dealsDbData.currencyCode,
            discountLabel = dealsDbData.discountLabel,
            isAddedToFavorite = isAddedToFavorite
        )
        return dealData
    }

    override fun getDealsDescriptionDetailsList(): Flow<Resource<DealsDetailsModel>> = flow {
        emit(Resource.Loading())

        try {
            val responseBody: ResponseBody =
                dealsDescriptionApi.getDealsDescriptionData("unique")

            // Used to decode json response body, json is not correct,
            // has additional trailing comma on line 26
            val dealsDescriptionData = decodeJsonResponse(responseBody)

            Timber.d("ApiData: $dealsDescriptionData")

            val detailsData = DealsDetailsModel(
                uniqueId = dealsDescriptionData.unique,
                title = dealsDescriptionData.title,
                company = dealsDescriptionData.company,
                description = dealsDescriptionData.description,
                city = dealsDescriptionData.city,
                soldLabel = dealsDescriptionData.soldLabel,
                originalPrice = dealsDescriptionData.prices.price.amount,
                currencySymbol = dealsDescriptionData.prices.price.currency.symbol,
                currencyCode = dealsDescriptionData.prices.price.currency.code,
                fromPrice = dealsDescriptionData.prices.fromPrice.amount,
                priceLabel = dealsDescriptionData.prices.discountLabel,
                discountLabel = dealsDescriptionData.prices.priceLabel
            )

            emit(Resource.Success(detailsData))
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
    }

    private fun decodeJsonResponse(responseBody: ResponseBody): DealsDescriptionData {
        val source = responseBody.source()

        val buffer = Buffer()
        source.readAll(buffer)
        val jsonResponse = buffer.readString(Charset.defaultCharset())

        val cleanedJson = cleanJsonResponse(jsonResponse)

        val gson = Gson()
        val dealsDescriptionData = gson.fromJson(cleanedJson, DealsDescriptionData::class.java)

        return dealsDescriptionData
    }

    private fun cleanJsonResponse(json: String): String {
        return json.replace("""\,\s*\}""".toRegex(), "}")
            .replace("""\,\s*\]""".toRegex(), "]")
    }
}





