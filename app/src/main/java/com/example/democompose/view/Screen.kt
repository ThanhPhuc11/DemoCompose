package com.example.democompose.view

sealed class Screen(val route: String) {
    object MainScreen : Screen("SCREEN_MAIN")
    object DetailScreen : Screen("SCREEN_DETAIL")

    fun withArgs(vararg args: String?): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}
