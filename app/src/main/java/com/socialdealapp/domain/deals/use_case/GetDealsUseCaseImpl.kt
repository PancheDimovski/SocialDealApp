package com.socialdealapp.domain.deals.use_case

import com.socialdealapp.domain.deals.model.DealsModel
import com.socialdealapp.domain.deals.repository.DealsRepository
import com.socialdealapp.utils.Resource
import kotlinx.coroutines.flow.Flow

class GetDealsUseCaseImpl(private val repository: DealsRepository): GetDealsUseCase {

    override fun getDealsData(): Flow<Resource<List<DealsModel>>> {
        return repository.getDealsList()
    }


}