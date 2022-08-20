package com.example.democompose.view.main

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class MainViewModel(private val repo: MainRepository) : ViewModel() {
    var str = mutableStateOf("")

    fun getUnit() {
        viewModelScope.launch {
            repo.getUnit("current")
                .catch { }
                .collect {

                }
        }
    }

    override fun onCleared() {
        super.onCleared()
        Log.e("mainviewmodel", "onCleared")
    }
}