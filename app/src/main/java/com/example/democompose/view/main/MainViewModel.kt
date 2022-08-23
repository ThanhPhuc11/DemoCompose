package com.example.democompose.view.main

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.democompose.model.ChampionModel
import com.example.democompose.utils.AppConstants
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class MainViewModel(private val useCase: MainUseCase) : ViewModel() {
    var listChampion = mutableStateListOf<ChampionModel>()

    init {
        getUnit()
    }

    fun getUnit() {
        viewModelScope.launch {
            useCase.getUnit()
                .onStart {  }
                .onCompletion {  }
                .catch {
                    Log.e("catch", it.message.toString())
                }
                .collect {
                    listChampion.clear()
                    it.champions?.forEach { championObj ->
                        listChampion.add(championObj)
                    }
                }
        }
    }

    fun getBaseImageChamp() : String {
        return useCase.getBaseImageChamp()
    }
}