package com.example.democompose.view.detail

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.democompose.model.ChampionModel

class DetailViewModel: ViewModel() {
    val championObj = mutableStateOf(ChampionModel())
    override fun onCleared() {
        super.onCleared()
        Log.e("detailviewmodel", "onCleared")
    }
}