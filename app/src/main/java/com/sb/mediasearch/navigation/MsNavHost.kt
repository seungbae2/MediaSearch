package com.sb.mediasearch.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.sb.mediasearch.feature.bookmarks.navigation.bookmarksScreen
import com.sb.mediasearch.feature.search.navigation.SEARCH_ROUTE
import com.sb.mediasearch.feature.search.navigation.searchScreen
import com.sb.mediasearch.ui.AppState


@Composable
fun MsNavHost(
    appState: AppState,
    onShowSnackbar: suspend (String, String?) -> Boolean,
    modifier: Modifier = Modifier,
    startDestination: String = SEARCH_ROUTE,
) {
    val navController = appState.navController
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
        enterTransition = {
            EnterTransition.None
        },
        exitTransition = {
            ExitTransition.None
        }
    ) {
        searchScreen()
        bookmarksScreen()
    }
}