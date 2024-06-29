package com.sb.mediasearch.feature.search

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
internal fun SearchRoute(
    viewModel: SearchViewModel = hiltViewModel(),
) {
    SearchScreen()
}

@Composable
internal fun SearchScreen() {
    Text("Search Screen")

}