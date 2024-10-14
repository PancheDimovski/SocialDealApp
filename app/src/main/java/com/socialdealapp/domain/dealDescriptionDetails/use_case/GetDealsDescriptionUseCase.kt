package com.socialdealapp.domain.dealDescriptionDetails.use_case

import com.socialdealapp.domain.dealDescriptionDetails.model.DealsDetailsModel
import com.socialdealapp.domain.deals.model.DealsModel
import com.socialdealapp.utils.Resource
import kotlinx.coroutines.flow.Flow

interface GetDealsDescriptionUseCase {
    suspend fun getDealsDescriptionData(): Flow<Resource<DealsDetailsModel>>
    suspend fun getDealsData(uniqueId: String): Flow<Resource<DealsModel>>
}