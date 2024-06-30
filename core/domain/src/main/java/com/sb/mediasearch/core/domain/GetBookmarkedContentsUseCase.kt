package com.sb.mediasearch.core.domain

import com.sb.mediasearch.core.data.repository.BookmarkRepository
import com.sb.mediasearch.core.model.Content
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetBookmarkedContentsUseCase @Inject constructor(
    private val bookmarkRepository: BookmarkRepository
){
    operator fun invoke(): Flow<List<Content>> = bookmarkRepository.getBookmarks()
}