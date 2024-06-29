package com.sb.mediasearch.core.network

import com.sb.mediasearch.core.network.model.ImageSearchResponse
import com.sb.mediasearch.core.network.model.VideoSearchResponse
import com.skydoves.sandwich.ApiResponse

interface KakaoNetworkDataSource {
    suspend fun searchVideoContents(
        query: String,
        sort: String,
        page: Int,
        size: Int
    ) : ApiResponse<VideoSearchResponse>

    suspend fun searchImageContents(
        query: String,
        sort: String,
        page: Int,
        size: Int
    ) : ApiResponse<ImageSearchResponse>
}