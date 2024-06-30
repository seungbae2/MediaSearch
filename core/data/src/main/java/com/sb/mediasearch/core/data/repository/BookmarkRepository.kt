package com.sb.mediasearch.core.data.repository

import com.sb.mediasearch.core.model.Content
import kotlinx.coroutines.flow.Flow

interface BookmarkRepository {
    fun getBookmarks(): Flow<List<Content>>
    suspend fun insertOrReplaceBookmarkedContent(content: Content)
    suspend fun deleteBookmarkedContent(content: Content)
}