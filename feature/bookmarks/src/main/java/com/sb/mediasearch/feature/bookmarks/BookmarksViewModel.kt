package com.sb.mediasearch.feature.bookmarks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sb.mediasearch.core.domain.GetBookmarkedContentsUseCase
import com.sb.mediasearch.core.model.Content
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class BookmarksViewModel @Inject constructor(
    getBookmarkedContentsUseCase: GetBookmarkedContentsUseCase
) : ViewModel() {

    val bookmarkedContentsUiState: StateFlow<BookmarkedContentsUiState> =
        getBookmarkedContentsUseCase.invoke().map {
            if (it.isEmpty()) {
                BookmarkedContentsUiState.Empty
            } else {
                BookmarkedContentsUiState.Success(it)
            }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = BookmarkedContentsUiState.Loading
        )
}

sealed interface BookmarkedContentsUiState {
    data class Success(val bookmarkedContents: List<Content>) : BookmarkedContentsUiState
    data object Error: BookmarkedContentsUiState
    data object Loading: BookmarkedContentsUiState
    data object Empty: BookmarkedContentsUiState
}