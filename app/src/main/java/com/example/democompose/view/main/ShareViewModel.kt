package com.example.democompose.view.main

import android.util.Log
import androidx.lifecycle.ViewModel

class ShareViewModel: ViewModel() {
    var shareText = "1"
    override fun onCleared() {
        super.onCleared()
        Log.e("shareviewmodel", "onCleared")
    }
}