package com.example.democompose.view.main

import com.example.democompose.network.ApiService
import com.example.democompose.network.ApiServiceVN
import kotlinx.coroutines.flow.flow

class MainRepositoryVN(private val apiService: ApiServiceVN) {
    suspend fun getChampion() = flow {
        emit(apiService.getChampion())
    }
}