package com.socialdealapp.domain.deals.use_case

import com.socialdealapp.domain.deals.model.DealsModel
import com.socialdealapp.utils.Resource
import kotlinx.coroutines.flow.Flow

interface GetDealsUseCase {

    fun getDealsData(): Flow<Resource<List<DealsModel>>>

}