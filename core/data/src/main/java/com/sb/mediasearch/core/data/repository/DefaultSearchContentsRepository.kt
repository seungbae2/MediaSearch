package com.sb.mediasearch.core.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.sb.mediasearch.core.common.network.Dispatcher
import com.sb.mediasearch.core.common.network.MsDispatchers
import com.sb.mediasearch.core.model.Content
import com.sb.mediasearch.core.network.KakaoNetworkDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DefaultSearchContentsRepository @Inject constructor(
    @Dispatcher(MsDispatchers.IO) private val ioDispatcher: CoroutineDispatcher,
    private val kakaoNetwork: KakaoNetworkDataSource,
) : SearchContentsRepository {
    override suspend fun searchContents(
        query: String,
        sort: String,
    ): Flow<PagingData<Content>> {
        return Pager(
            config = PagingConfig(
                pageSize = 30,
                prefetchDistance = 5,
            ),
            pagingSourceFactory = { SearchContentsPagingSource(ioDispatcher, kakaoNetwork, query, sort) }
        ).flow
    }
}