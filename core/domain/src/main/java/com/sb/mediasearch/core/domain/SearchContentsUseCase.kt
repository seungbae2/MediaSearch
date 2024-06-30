package com.sb.mediasearch.core.domain

import com.sb.mediasearch.core.data.repository.SearchContentsRepository
import javax.inject.Inject

class SearchContentsUseCase @Inject constructor(
    private val searchContentsRepository: SearchContentsRepository
){
    suspend operator fun invoke(query: String, sort: String) =
        searchContentsRepository.searchContents(query, sort)
}