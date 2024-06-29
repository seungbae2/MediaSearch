package com.sb.mediasearch.core.network.model

import kotlinx.serialization.Serializable
import kotlinx.datetime.Instant

@Serializable
data class VideoResponse(
    val title: String,
    val playTime: Int,
    val thumbnail: String,
    val url: String,
    val datetime: Instant,
    val author: String,
)
