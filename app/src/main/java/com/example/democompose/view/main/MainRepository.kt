package com.example.democompose.view.main

import com.example.democompose.network.ApiService
import kotlinx.coroutines.flow.flow

class MainRepository(private val apiService: ApiService) {
    suspend fun getUnit(patch: String) = flow {
        emit(apiService.getUnit(patch))
    }
}