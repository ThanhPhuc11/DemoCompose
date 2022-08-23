package com.example.democompose.view.detail

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.democompose.model.ChampionModel

class DetailViewModel : ViewModel() {
    val championObj = mutableStateOf(ChampionModel())

    fun getUrlImageTrait(index: Int): String {
        val trait = championObj.value.traits?.get(index)?.traitId?.split("_")?.get(1)?.lowercase()
        return "https://dtcl.lol/trait/img2/$trait.png"
    }


    override fun onCleared() {
        super.onCleared()
        Log.e("detailviewmodel", "onCleared")
    }
}