package com.example.democompose.view.detail

import android.os.Bundle
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
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
                    viewModel.championObj.value = shareViewModel.championModel.value
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
            //Image Champ
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
                    viewModel.championObj.value.traits?.forEachIndexed { index, trait ->
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(top = 7.dp)
                        ) {
                            GlideImage(
                                imageModel = viewModel.getUrlImageTrait(index),
                                error = painterResource(R.drawable.ic_default),
                                modifier = Modifier
                                    .padding(end = 10.dp)
                                    .size(25.dp, 25.dp)
                            )
                            Text(
                                trait.name ?: "",
                                color = ColorUtils.white_FFFFFF,
                                fontSize = 14.sp
                            )
                        }
                    }
                }
            }

            //TODO: Skill
            Text(
                "Kỹ Năng",
                color = ColorUtils.white_FFFFFF,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .padding(top = 20.dp, bottom = 10.dp)
            )
            Column(
                Modifier
                    .padding(horizontal = 10.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(ColorUtils.purple_3F3872)
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(
                    viewModel.championObj.value.skill?.name ?: "",
                    color = ColorUtils.white_FFFFFF,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
                Row(Modifier.padding(top = 16.dp)) {
                    GlideImage(
                        imageModel = "https://dtcl.lol/skill/${viewModel.championObj.value.skill?.img}",
                        modifier = Modifier
                            .padding(end = 16.dp)
                            .size(60.dp, 60.dp)
                    )
                    Text(
                        viewModel.championObj.value.skill?.description ?: "",
                        color = ColorUtils.white_FFFFFF,
                        fontSize = 14.sp
                    )
                }

            }

        }
    }
}