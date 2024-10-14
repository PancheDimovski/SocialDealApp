package com.socialdealapp.domain.dealDescriptionDetails.use_case

import com.socialdealapp.domain.dealDescriptionDetails.model.DealsDetailsModel
import com.socialdealapp.domain.dealDescriptionDetails.repository.DealsDescriptionDetailsRepository
import com.socialdealapp.domain.deals.model.DealsModel
import com.socialdealapp.utils.Resource
import kotlinx.coroutines.flow.Flow

class GetDealsDescriptionUseCaseImpl(private val repository: DealsDescriptionDetailsRepository) :
    GetDealsDescriptionUseCase {
    override suspend fun getDealsDescriptionData(): Flow<Resource<DealsDetailsModel>> {
        return repository.getDealsDescriptionDetailsList()
    }

    override suspend fun getDealsData(uniqueId: String): Flow<Resource<DealsModel>> {
        return repository.getDealsData(uniqueId)
    }

}