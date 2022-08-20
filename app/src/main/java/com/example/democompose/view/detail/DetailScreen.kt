package com.example.democompose.view.detail

import android.os.Bundle
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.navigation.NavHostController
import com.example.democompose.model.UserModel
import com.example.democompose.view.Screen
import com.example.democompose.view.main.ShareViewModel
import com.google.gson.Gson
import org.koin.androidx.compose.getViewModel

@Composable
fun DetailScreen(navController: NavHostController, arguments: Bundle?) {
    val vm = getViewModel<DetailViewModel>()
    val shareVM = ViewModelProvider(navController.getBackStackEntry(Screen.MainScreen.route))[ShareViewModel::class.java]
    Log.e("shareDetail", shareVM.toString())
    Column(
        Modifier
            .fillMaxSize()
            .background(Color.Red)
    ) {
        val text = remember { navController.currentBackStackEntry?.arguments?.getString("userId") }
        val text2 =
            remember { navController.currentBackStackEntry?.arguments?.getSerializable("userModel") as UserModel? }
        Text(
            text = "Screen 2! ${
                Gson().fromJson(
                    arguments?.getString("userId"),
                    UserModel::class.java
                )?.name
            } ${arguments?.getString("nameId")}",
            Modifier.clickable {
                navController.popBackStack()
            })
    }
}