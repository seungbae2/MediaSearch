package com.sb.mediasearch.feature.search

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.sb.mediasearch.core.domain.SearchContentsUseCase
import com.sb.mediasearch.core.model.Content
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.time.Duration.Companion.milliseconds

@OptIn(FlowPreview::class)
@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchContentsUseCase: SearchContentsUseCase,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {
    val searchQuery = savedStateHandle.getStateFlow(key = SEARCH_QUERY, initialValue = "")

    fun onSearchQueryChanged(query: String) {
        savedStateHandle[SEARCH_QUERY] = query
    }

    private val _searchPagingResult = MutableStateFlow<PagingData<Content>>(PagingData.empty())
    val searchPagingResult: StateFlow<PagingData<Content>> = _searchPagingResult.asStateFlow()

    init {
        viewModelScope.launch {
            searchQuery
                .debounce(300.milliseconds) // Use Duration for debounce
                .filter { it.length >= SEARCH_QUERY_MIN_LENGTH }
                .distinctUntilChanged()
                .flatMapLatest { query ->
                    searchContentsUseCase.invoke(query, "recency")
                        .cachedIn(viewModelScope)
                }
                .collect { pagingData ->
                    _searchPagingResult.value = pagingData
                }
        }
    }

    companion object {
        private const val SEARCH_QUERY_MIN_LENGTH = 2
        private const val SEARCH_QUERY = "searchQuery"
    }
}
