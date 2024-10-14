package com.socialdealapp.domain.dealDescriptionDetails.repository

import com.socialdealapp.domain.dealDescriptionDetails.model.DealsDetailsModel
import com.socialdealapp.domain.deals.model.DealsModel
import com.socialdealapp.utils.Resource
import kotlinx.coroutines.flow.Flow

interface DealsDescriptionDetailsRepository {
    fun getDealsDescriptionDetailsList(): Flow<Resource<DealsDetailsModel>>
    suspend fun  getDealsData(uniqueId: String): Flow<Resource<DealsModel>>
}