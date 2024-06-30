package com.sb.mediasearch.core.data.repository

import androidx.paging.PagingData
import com.sb.mediasearch.core.model.Content
import kotlinx.coroutines.flow.Flow

interface SearchContentsRepository {
    suspend fun searchContents(
        query: String,
        sort: String,
    ): Flow<PagingData<Content>>

}