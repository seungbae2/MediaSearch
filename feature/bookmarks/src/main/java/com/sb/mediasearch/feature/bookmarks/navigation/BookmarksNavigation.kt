package com.sb.mediasearch.feature.bookmarks.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.sb.mediasearch.feature.bookmarks.BookmarksRoute


const val BOOKMARKS_ROUTE = "bookmarks"

fun NavController.navigateToBookmarks(navOptions: NavOptions) = navigate(BOOKMARKS_ROUTE, navOptions)

fun NavGraphBuilder.bookmarksScreen() {
    composable(
        route = BOOKMARKS_ROUTE,
    ) {
        BookmarksRoute()
    }
}