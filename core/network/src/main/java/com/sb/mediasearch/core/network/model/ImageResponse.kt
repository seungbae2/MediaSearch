package com.sb.mediasearch.core.network.model

import kotlinx.datetime.Instant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ImageResponse(
    val collection: String,
    @SerialName("thumbnail_url")
    val thumbnailUrl: String,
    @SerialName("image_url")
    val imageUrl: String,
    val width: Int,
    val height: Int,
    @SerialName("display_sitename")
    val displaySitename: String,
    val docUrl: String,
    val datetime: Instant,
)
