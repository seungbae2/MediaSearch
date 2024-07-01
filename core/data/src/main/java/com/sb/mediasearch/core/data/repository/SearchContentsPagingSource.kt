package com.sb.mediasearch.core.data.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.sb.mediasearch.core.common.network.Dispatcher
import com.sb.mediasearch.core.common.network.MsDispatchers
import com.sb.mediasearch.core.data.mapper.toContentData
import com.sb.mediasearch.core.model.Content
import com.sb.mediasearch.core.network.KakaoNetworkDataSource
import com.skydoves.sandwich.ApiResponse
import com.skydoves.sandwich.onSuccess
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext

internal class SearchContentsPagingSource (
    @Dispatcher(MsDispatchers.IO) private val ioDispatcher: CoroutineDispatcher,
    private val kakaoNetwork: KakaoNetworkDataSource,
    private val query: String,
    private val sort: String,
) : PagingSource<Int, Content>() {
    override fun getRefreshKey(state: PagingState<Int, Content>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Content> {
        return try {
            val currentPage = params.key ?: INIT_PAGE

            withContext(ioDispatcher) {
                // async를 사용하여 네트워크 호출을 병렬로 수행
                val videoDeferred = async {
                    kakaoNetwork.searchVideoContents(
                        query = query,
                        sort = sort,
                        page = currentPage,
                        size = PAGE_SIZE,
                    )
                }
                val imageDeferred = async {
                    kakaoNetwork.searchImageContents(
                        query = query,
                        sort = sort,
                        page = currentPage,
                        size = PAGE_SIZE,
                    )
                }

                val videoResponse = videoDeferred.await()
                val imageResponse = imageDeferred.await()

                val videoContents = mutableListOf<Content>()
                val imageContents = mutableListOf<Content>()

                var videoIsEnd = false
                var imageIsEnd = false

                // 비디오 응답 처리
                videoResponse.onSuccess {
                    videoIsEnd = data.meta.isEnd
                    if (!videoIsEnd) {
                        videoContents.addAll(data.documents.map { video ->
                            video.toContentData()
                        })
                    }
                }

                // 이미지 응답 처리
                imageResponse.onSuccess {
                    imageIsEnd = data.meta.isEnd
                    if (!imageIsEnd) {
                        imageContents.addAll(data.documents.map { image ->
                            image.toContentData()
                        })
                    }
                }

                if (videoResponse is ApiResponse.Success && imageResponse is ApiResponse.Success) {
                    val combinedContents: List<Content> = (videoContents + imageContents).sortedByDescending { it.datetime }
                    val isEnd = videoIsEnd && imageIsEnd

                    LoadResult.Page(
                        data = combinedContents,
                        prevKey = if (currentPage == INIT_PAGE) null else currentPage - 1,
                        nextKey = if (isEnd) null else currentPage + 1
                    )
                } else {
                    LoadResult.Error(Exception("Failed to load data"))
                }
            }
        } catch (exception: Exception) {
            return LoadResult.Error(exception)
        }
    }

    companion object {
        const val INIT_PAGE = 1
        const val PAGE_SIZE = 30
    }
}