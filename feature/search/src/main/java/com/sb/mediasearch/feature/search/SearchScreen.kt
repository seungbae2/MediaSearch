package com.sb.mediasearch.feature.search

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.sb.mediasearch.core.model.Content

@Composable
internal fun SearchRoute(
    viewModel: SearchViewModel = hiltViewModel(),
) {
    val searchPagingResult: LazyPagingItems<Content> = viewModel.searchPagingResult.collectAsLazyPagingItems()
    SearchScreen(searchPagingResult)
}

@Composable
internal fun SearchScreen(
    searchPagingResult: LazyPagingItems<Content>
) {
    LazyColumn {
        items(searchPagingResult.itemCount) { index ->
            searchPagingResult[index]?.let { Text(it.thumbnailUrl) }
        }
    }
}