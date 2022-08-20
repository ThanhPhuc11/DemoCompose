package com.example.democompose.network

import com.example.democompose.model.BaseResponse
import com.example.democompose.model.UnitModel
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {

    @GET("tft-stat-api/units")
    suspend fun getUnit(@Query("patch") patch: String): BaseResponse<List<UnitModel>>
}