package com.example.democompose.view.main

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.democompose.model.ChampionModel

class ShareViewModel: ViewModel() {
    var championModel = mutableStateOf(ChampionModel())
    override fun onCleared() {
        super.onCleared()
        Log.e("shareviewmodel", "onCleared")
    }
}