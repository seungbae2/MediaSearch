package com.sb.mediasearch.core.network.model

import kotlinx.serialization.Serializable

@Serializable
data class ImageSearchResponse(
    val documents: List<ImageResponse>,
    val meta: MetaResponse
)
