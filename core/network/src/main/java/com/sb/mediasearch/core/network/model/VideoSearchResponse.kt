package com.sb.mediasearch.core.network.model

import kotlinx.serialization.Serializable

@Serializable
data class VideoSearchResponse(
    val documents: List<VideoResponse>,
    val metaResponse: MetaResponse
)
