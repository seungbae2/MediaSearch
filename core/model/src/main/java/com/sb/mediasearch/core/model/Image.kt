package com.sb.mediasearch.core.model

import kotlinx.datetime.Instant

data class Image(
    val collection: String,
    val thumbnailUrl: String,
    val imageUrl: String,
    val width: Int,
    val height: Int,
    val displaySitename: String,
    val docUrl: String,
    val datetime: Instant,
)
