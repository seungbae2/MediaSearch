package com.sb.mediasearch.core.data.repository

import com.sb.mediasearch.core.model.Content
import kotlinx.coroutines.flow.Flow

interface BookmarkRepository {

    fun getBookmarks(): Flow<List<Content>>

    suspend fun bookmarkContent(content: Content)

    suspend fun deleteAllBookmarks()
}