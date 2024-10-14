package com.socialdealapp.domain.deals.repository


import com.socialdealapp.domain.deals.model.DealsModel
import com.socialdealapp.utils.Resource
import kotlinx.coroutines.flow.Flow

interface DealsRepository {

    fun getDealsList(): Flow<Resource<List<DealsModel>>>
}