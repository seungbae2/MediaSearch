package com.sb.mediasearch.feature.search.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.sb.mediasearch.feature.search.SearchRoute

const val SEARCH_ROUTE = "search"

fun NavController.navigateToSearch(navOptions: NavOptions) = navigate(SEARCH_ROUTE, navOptions)

fun NavGraphBuilder.searchScreen() {
    composable(
        route = SEARCH_ROUTE,
    ) {
        SearchRoute()
    }
}