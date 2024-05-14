package com.michael.products.presentation.utils

sealed class Screen (val route: String){
    data object ProductScreen: Screen("product_screen")
    data object DetailsScreen: Screen("details_screen")
}