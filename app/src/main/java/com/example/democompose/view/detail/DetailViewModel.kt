package com.example.democompose.view.detail

import android.util.Log
import androidx.lifecycle.ViewModel

class DetailViewModel: ViewModel() {
    override fun onCleared() {
        super.onCleared()
        Log.e("detailviewmodel", "onCleared")
    }
}