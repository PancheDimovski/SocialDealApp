package com.socialdealapp.data.deals.remote

import retrofit2.http.GET

interface DealsApiService {

    @GET("deals.json")
    suspend fun getDealsData(): DealsData
}