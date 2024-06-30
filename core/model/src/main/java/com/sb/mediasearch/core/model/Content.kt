package com.sb.mediasearch.core.model

import kotlinx.datetime.Instant

data class Content(
    val playTime: Int?,
    val thumbnailUrl: String,
    val url: String,
    val datetime: Instant,
    val type: ContentType,
)
