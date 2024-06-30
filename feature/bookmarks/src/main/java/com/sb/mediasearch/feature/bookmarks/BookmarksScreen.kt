package com.sb.mediasearch.feature.bookmarks

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sb.mediasearch.core.designsystem.component.DynamicAsyncImage

@Composable
internal fun BookmarksRoute(
    viewModel: BookmarksViewModel = hiltViewModel(),
) {
    val bookmarkedContentsUiState by viewModel.bookmarkedContentsUiState.collectAsStateWithLifecycle()
    BookmarksScreen(bookmarkedContentsUiState)
}

@Composable
internal fun BookmarksScreen(
    bookmarkedContentsUiState: BookmarkedContentsUiState
) {
    when (bookmarkedContentsUiState) {
        BookmarkedContentsUiState.Empty,
        BookmarkedContentsUiState.Error -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = "No results found")
            }
        }
        BookmarkedContentsUiState.Loading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = "Loading")
            }
        }
        is BookmarkedContentsUiState.Success -> {
            LazyVerticalGrid(
                columns = GridCells.Adaptive(minSize = 128.dp),
                contentPadding = PaddingValues(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(bookmarkedContentsUiState.bookmarkedContents.size) { index ->
                    DynamicAsyncImage(
                        imageUrl = bookmarkedContentsUiState.bookmarkedContents[index].thumbnailUrl,
                    )
                }
            }
        }
    }
}