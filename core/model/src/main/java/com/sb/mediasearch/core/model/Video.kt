package com.sb.mediasearch.core.model

import kotlinx.datetime.Instant

data class Video(
    val title: String,
    val playTime: Int,
    val thumbnail: String,
    val url: String,
    val datetime: Instant,
)
