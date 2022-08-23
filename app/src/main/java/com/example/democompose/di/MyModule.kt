package com.example.democompose.di

import com.example.democompose.network.ApiService
import com.example.democompose.network.ApiServiceVN
import com.example.democompose.network.RetrofitBuilder
import com.example.democompose.view.detail.DetailUseCase
import com.example.democompose.view.detail.DetailViewModel
import com.example.democompose.view.main.MainRepository
import com.example.democompose.view.main.MainRepositoryVN
import com.example.democompose.view.main.MainUseCase
import com.example.democompose.view.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.create

val appModule = module {
    viewModel { MainViewModel(get()) }
    viewModel { DetailViewModel(get()) }

    single { MainUseCase(get()) }
    single { DetailUseCase() }

//    single { MainRepository(get()) }
    single { MainRepositoryVN(get()) }

//    single { RetrofitBuilder.getInstance()?.create(ApiService::class.java) }
    single { RetrofitBuilder.getInstance(RetrofitBuilder.RetrofitType.DTCL_LOL)?.create(ApiServiceVN::class.java) }
}