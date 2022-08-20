package com.example.democompose.di

import com.example.democompose.network.ApiService
import com.example.democompose.network.RetrofitBuilder
import com.example.democompose.view.detail.DetailViewModel
import com.example.democompose.view.main.MainRepository
import com.example.democompose.view.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.create

val appModule = module {
    viewModel { MainViewModel(get()) }
    viewModel { DetailViewModel() }

    single { MainRepository(get()) }

    single { RetrofitBuilder.getInstance()?.create(ApiService::class.java) }
}