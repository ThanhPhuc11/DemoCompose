package com.example.democompose.network

import com.example.democompose.model.BaseResponse
import com.example.democompose.model.GameDataModel
import com.example.democompose.model.UnitModel
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiServiceVN {

    @GET("api/v1/champion")
    suspend fun getChampion(): GameDataModel
}