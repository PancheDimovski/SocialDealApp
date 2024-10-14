package com.socialdealapp.data.dealsDescription.remote

import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Query

interface DealsDescriptionApi {

    @GET("details.json")
    suspend fun getDealsDescriptionData(
        @Query("id") uniqueId: String
    ): ResponseBody

}