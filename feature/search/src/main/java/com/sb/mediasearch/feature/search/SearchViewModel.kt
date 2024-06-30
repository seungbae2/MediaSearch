package com.sb.mediasearch.feature.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.sb.mediasearch.core.domain.SearchContentsUseCase
import com.sb.mediasearch.core.model.Content
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchContentsUseCase: SearchContentsUseCase
) : ViewModel() {
    private val _searchPagingResult = MutableStateFlow<PagingData<Content>>(PagingData.empty())
    val searchPagingResult: StateFlow<PagingData<Content>> = _searchPagingResult.asStateFlow()

    init {
        viewModelScope.launch {
            searchContentsUseCase.invoke("코틀린","recency")
                .cachedIn(viewModelScope)
                .collect{
                    _searchPagingResult.value = it
                }
        }
    }
}