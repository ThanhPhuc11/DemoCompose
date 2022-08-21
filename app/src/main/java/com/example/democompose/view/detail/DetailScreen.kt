package com.example.democompose.view.detail

import android.os.Bundle
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavHostController
import com.example.democompose.BuildConfig
import com.example.democompose.R
import com.example.democompose.ui.theme.BaseComposeScreen
import com.example.democompose.ui.theme.ColorUtils
import com.example.democompose.view.Screen
import com.example.democompose.view.main.ShareViewModel
import com.skydoves.landscapist.glide.GlideImage
import org.koin.androidx.compose.getViewModel

@Composable
fun DetailScreen(navController: NavHostController, arguments: Bundle?) {
    val viewModel = getViewModel<DetailViewModel>()
    val shareViewModel =
        ViewModelProvider(navController.getBackStackEntry(Screen.MainScreen.route))[ShareViewModel::class.java]
    val lifecycleOwner = LocalLifecycleOwner.current
    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            when (event) {
                Lifecycle.Event.ON_CREATE -> {
//                    viewModel.championObj.value = Gson().fromJson(arguments?.getString("championId"), ChampionModel::class.java)
                }
                else -> {}
            }
        }

        // Add the observer to the lifecycle
        lifecycleOwner.lifecycle.addObserver(observer)

        // When the effect leaves the Composition, remove the observer
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }
    BaseComposeScreen {
        Column(
            Modifier
                .fillMaxSize()
                .background(ColorUtils.purple_2C2750)
                .verticalScroll(rememberScrollState())
        ) {
            ConstraintLayout {
                val (content, traits) = createRefs()
                GlideImage(
                    imageModel = "${BuildConfig.BASE_IMAGE_MOBALYTICS}${shareViewModel.championModel.value.slug}.jpg",
                    modifier = Modifier
                        .background(ColorUtils.black_000000)
                        .height(300.dp),
                    error = painterResource(R.drawable.ic_default),
                    contentScale = ContentScale.FillWidth,
//            requestOptions = {
//                RequestOptions()
//                    .diskCacheStrategy(DiskCacheStrategy.ALL)
//                    .centerInside()
//            }
                )
                Column(
                    Modifier
                        .constrainAs(traits) {
                            bottom.linkTo(parent.bottom)
                        }
                        .padding(10.dp)) {
                    shareViewModel.championModel.value.traits?.forEach {
                        Text(it.name ?: "", color = ColorUtils.white_FFFFFF, fontSize = 14.sp, modifier = Modifier.padding(top = 3.dp))
                    }
                }
            }

        }
    }
}