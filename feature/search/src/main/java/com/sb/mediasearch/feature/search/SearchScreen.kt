package com.sb.mediasearch.feature.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.sb.mediasearch.core.designsystem.component.DynamicAsyncImage
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
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 128.dp),
        contentPadding = PaddingValues(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(searchPagingResult.itemCount) { index ->
            searchPagingResult[index]?.thumbnailUrl?.let {
                DynamicAsyncImage(
                    imageUrl = it,
                )
            }
        }
    }
}