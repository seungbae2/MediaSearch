package com.sb.mediasearch.core.model

import kotlinx.datetime.Instant

data class Content(
    val uuid: String,
    val playTime: Int?,
    val thumbnailUrl: String,
    val url: String,
    val datetime: Instant,
    val type: String,
    val isBookmarked: Boolean = false,
)
