package com.example.democompose.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.platform.LocalFocusManager
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun BaseComposeScreen(modifier: Modifier = Modifier, layout: @Composable ColumnScope.() -> Unit) {
//    val focusManager = LocalFocusManager.current
    val systemUiController = rememberSystemUiController()
    systemUiController.setSystemBarsColor(
        color = ColorUtils.black_000000
    )
    Column(
        modifier = modifier
            .background(ColorUtils.white_FFFFFF)
            .fillMaxSize()
            .noRippleClickable { }
    ) {
        layout(this)
    }
}

fun Modifier.noRippleClickable(onClick: () -> Unit): Modifier = composed {
    val focusManager = LocalFocusManager.current
    clickable(indication = null,
        interactionSource = remember { MutableInteractionSource() }) {
        onClick()
        focusManager.clearFocus()
    }
}