package com.example.democompose.view.main

import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavHostController
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.democompose.BuildConfig
import com.example.democompose.R
import com.example.democompose.model.ChampionModel
import com.example.democompose.ui.theme.BaseComposeScreen
import com.example.democompose.ui.theme.ColorUtils
import com.example.democompose.ui.theme.noRippleClickable
import com.example.democompose.utils.AppConstants
import com.example.democompose.view.Screen
import com.skydoves.landscapist.glide.GlideImage
import org.koin.androidx.compose.getViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainScreen(navController: NavHostController) {
    val viewModel = getViewModel<MainViewModel>()
    val shareViewModel =
        ViewModelProvider(navController.getBackStackEntry(Screen.MainScreen.route))[ShareViewModel::class.java]
    val lifecycleOwner = LocalLifecycleOwner.current
    DisposableEffect(lifecycleOwner) {
        // Create an observer that triggers our remembered callbacks
        // for sending analytics events
        val observer = LifecycleEventObserver { _, event ->
            when (event) {
                Lifecycle.Event.ON_CREATE -> {
                    Log.e("phucdz", "ON_CREATE")
//                    viewModel.getUnit()
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
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }
    BaseComposeScreen(Modifier.verticalScroll(rememberScrollState())) {

        val list = viewModel.listChampion
        val baseImageUrl = viewModel.getBaseImageChamp()
        LazyVerticalGrid(
            cells = GridCells.Fixed(4),
            horizontalArrangement = Arrangement.spacedBy(5.dp),
            modifier = Modifier
                .background(ColorUtils.purple_2C2750)
                .fillMaxWidth()
                .heightIn(0.dp, 1000.dp)
                .wrapContentHeight()
        ) {
            itemsIndexed(list) { index, obj ->
                ItemChampion(obj, baseImageUrl) {
                    shareViewModel.championModel.value = obj
                    navController.navigate(
                        Screen.DetailScreen.withArgs(obj.championId)
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun ItemChampion(
    obj: ChampionModel = ChampionModel(),
    baseUrl: String = "",
    onClick: (() -> Unit)? = null
) {
    Column(
        Modifier
            .padding(vertical = 10.dp)
            .noRippleClickable {
                onClick?.invoke()
            },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val configuration = LocalConfiguration.current
        val screenWidth = configuration.screenWidthDp
        Box(
            Modifier
                .width((screenWidth / 5).dp)
                .height((screenWidth / 5).dp)
                .border(
                    width = 1.dp,
                    color = getColorByCost(obj.cost ?: 1),
                    shape = RoundedCornerShape(4.dp)
                )
        ) {
            GlideImage(
                imageModel = "$baseUrl${obj.image}",
                modifier = Modifier
                    .clip(RoundedCornerShape(4.dp)),
//                placeHolder = painterResource(R.drawable.ic_default),
//                error = painterResource(R.drawable.ic_default),
                requestOptions = {
                    RequestOptions()
                        .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                }
            )
        }
        Text(
            obj.name ?: "",
            color = ColorUtils.white_FFFFFF,
            fontSize = 14.sp,
            modifier = Modifier.padding(top = 5.dp)
        )
    }
}

fun getColorByCost(cost: Int): Color {
    return when (cost) {
        1 -> ColorUtils.color_cost_1
        2 -> ColorUtils.color_cost_2
        3 -> ColorUtils.color_cost_3
        4 -> ColorUtils.color_cost_4
        5 -> ColorUtils.color_cost_5
        else -> ColorUtils.color_cost_1
    }
}