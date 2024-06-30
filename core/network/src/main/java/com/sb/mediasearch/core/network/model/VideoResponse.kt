package com.sb.mediasearch.core.network.model

import kotlinx.serialization.Serializable
import kotlinx.datetime.Instant
import kotlinx.serialization.SerialName

@Serializable
data class VideoResponse(
    val title: String,
    @SerialName("play_time")
    val playTime: Int,
    val thumbnail: String,
    val url: String,
    val datetime: Instant,
    val author: String,
)
