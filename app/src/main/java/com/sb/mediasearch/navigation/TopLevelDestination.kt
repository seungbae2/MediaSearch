package com.sb.mediasearch.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmarks
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.rounded.Bookmarks
import androidx.compose.material.icons.rounded.Search
import androidx.compose.ui.graphics.vector.ImageVector
import com.sb.mediasearch.R

enum class TopLevelDestination(
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val iconTextId: Int,
    val titleTextId: Int,
){
    SEARCH(
        selectedIcon = Icons.Rounded.Search,
        unselectedIcon = Icons.Filled.Search,
        iconTextId = R.string.feature_search,
        titleTextId = R.string.feature_search
    ),
    BOOKMARKS(
        selectedIcon = Icons.Rounded.Bookmarks,
        unselectedIcon = Icons.Filled.Bookmarks,
        iconTextId = R.string.feature_bookmarks,
        titleTextId = R.string.feature_bookmarks
    )
}