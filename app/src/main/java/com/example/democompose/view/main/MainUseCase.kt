package com.example.democompose.view.main

import android.util.Log
import com.example.democompose.model.BaseResponse
import com.example.democompose.model.GameDataModel
import com.example.democompose.model.UnitModel
import com.example.democompose.network.ApiService
import com.example.democompose.network.ApiServiceVN
import com.example.democompose.utils.AppConstants
import kotlinx.coroutines.flow.Flow
import org.koin.java.KoinJavaComponent.inject


class MainUseCase(private val repoVN: MainRepositoryVN) {
//    val repo by inject<MainRepository>(ApiService::class.java)
//    val repoVN: MainRepositoryVN by inject(ApiServiceVN::class.java)

    suspend fun getUnit(): Flow<GameDataModel> {
        return repoVN.getChampion()
    }

    fun getBaseImageChamp() : String {
        return AppConstants.BASE_IMAGE_DTCL_LOL
    }
}