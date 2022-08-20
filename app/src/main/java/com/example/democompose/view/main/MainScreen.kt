package com.example.democompose.view.main

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavHostController
import com.example.democompose.model.UserModel
import com.example.democompose.ui.theme.BaseComposeScreen
import com.example.democompose.view.Screen
import com.google.gson.Gson
import org.koin.androidx.compose.getViewModel

@Composable
fun MainScreen(navController: NavHostController) {
    val viewModel = getViewModel<MainViewModel>()
    val shareVM =
        ViewModelProvider(navController.getBackStackEntry(Screen.MainScreen.route))[ShareViewModel::class.java]
    Log.e("shareMain", shareVM.toString())
    val lifecycleOwner = LocalLifecycleOwner.current
    DisposableEffect(lifecycleOwner) {
        // Create an observer that triggers our remembered callbacks
        // for sending analytics events
        val observer = LifecycleEventObserver { _, event ->
            when (event) {
                Lifecycle.Event.ON_CREATE -> {
                    Log.e("phucdz", "ON_CREATE")
                }
                Lifecycle.Event.ON_START -> {
                    Log.e("phucdz", "ON_START")
                }
                Lifecycle.Event.ON_RESUME -> {
                    Log.e("phucdz", "ON_RESUME")
                }
                Lifecycle.Event.ON_PAUSE -> {
                    Log.e("phucdz", "ON_PAUSE")
                }
                Lifecycle.Event.ON_STOP -> {
                    Log.e("phucdz", "ON_STOP")
                }
                Lifecycle.Event.ON_DESTROY -> {
                    Log.e("phucdz", "ON_DESTROY")
                }
                Lifecycle.Event.ON_ANY -> {
                    Log.e("phucdz", "ON_ANY")
                }
                else -> {
                    Log.e("phucdz", "else")
                }
            }
        }

        // Add the observer to the lifecycle
        lifecycleOwner.lifecycle.addObserver(observer)

        // When the effect leaves the Composition, remove the observer
        onDispose {
            Log.e("phucdz", "onDispose")
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }
    BaseComposeScreen {
        Text(text = "Hello ${viewModel.str.value}", Modifier.clickable {
            navController.navigate(
                Screen.DetailScreen.withArgs(
                    Gson().toJson(UserModel("phucGson")),
                    null
                )
            )
        })
        Text(text = "Hello screen2!", Modifier.clickable {
            viewModel.str.value = "456"
            shareVM.shareText = "100"
//            navController.navigate(Screen.DetailScreen.withArgs(null, "nameIDDD"))
            viewModel.getUnit()
        })
    }
}