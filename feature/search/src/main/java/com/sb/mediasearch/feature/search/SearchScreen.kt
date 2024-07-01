package com.sb.mediasearch.feature.search

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.sb.mediasearch.core.common.instant.formatToDateTimeString
import com.sb.mediasearch.core.designsystem.component.DynamicAsyncImage
import com.sb.mediasearch.core.designsystem.icon.MsIcons
import com.sb.mediasearch.core.model.Content
import com.sb.mediasearch.feature.search.component.SearchToolbar

@Composable
internal fun SearchRoute(
    modifier: Modifier = Modifier,
    viewModel: SearchViewModel = hiltViewModel(),
) {
    val searchPagingResult: LazyPagingItems<Content> = viewModel.searchPagingResult.collectAsLazyPagingItems()
    val searchQuery by viewModel.searchQuery.collectAsStateWithLifecycle()

    SearchScreen(
        modifier = modifier,
        searchQuery = searchQuery,
        onSearchQueryChanged = viewModel::onSearchQueryChanged,
        onClickBookmark = viewModel::bookmarkContent,
        searchPagingResult = searchPagingResult,
    )
}

@Composable
internal fun SearchScreen(
    modifier: Modifier = Modifier,
    searchQuery: String = "",
    onSearchQueryChanged: (String) -> Unit = {},
    onClickBookmark: (Content) -> Unit = {},
    searchPagingResult: LazyPagingItems<Content>
) {
    Column(modifier = modifier) {
        SearchToolbar(
            searchQuery = searchQuery,
            onSearchQueryChanged = onSearchQueryChanged,
        )
        val loadState = searchPagingResult.loadState

        when {
            loadState.refresh is LoadState.Loading -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }
            loadState.refresh is LoadState.Error -> {
                val e = loadState.refresh as LoadState.Error
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(text = "Error: ${e.error.localizedMessage}", color = Color.Red)
                }
            }
            searchPagingResult.itemCount == 0 -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(text = "No results found")
                }
            }
            else -> {
                LazyVerticalGrid(
                    columns = GridCells.Adaptive(minSize = 128.dp),
                    contentPadding = PaddingValues(8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(searchPagingResult.itemCount) { index ->
                        searchPagingResult[index]?.let {
                            ItemCard(
                                content = it,
                                onClickBookmark = onClickBookmark
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ItemCard(content: Content, onClickBookmark: (Content) -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
//            .clickable { onClick(content) }
            .padding(8.dp)
    ) {
        DynamicAsyncImage(
            imageUrl = content.thumbnailUrl,
        )
        Text(
            text = content.datetime.formatToDateTimeString(),
            style = MaterialTheme.typography.bodySmall.copy(color = Color.White),
            modifier = Modifier
                .align(Alignment.BottomStart)
                .background(Color.Black.copy(alpha = 0.5f))
                .padding(4.dp)
        )
        Icon(
            imageVector = if (content.isBookmarked) MsIcons.Bookmark else MsIcons.BookmarkBorder,
            contentDescription = null,
            tint = Color.Yellow,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(8.dp)
                .clickable { onClickBookmark(content) }
        )
    }
}