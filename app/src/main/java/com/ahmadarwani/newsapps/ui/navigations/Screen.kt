package com.ahmadarwani.newsapps.ui.navigations

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Detail : Screen("detail")
}
