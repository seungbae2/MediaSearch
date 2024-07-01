package com.sb.mediasearch.core.data.mapper

import com.sb.mediasearch.core.model.Content
import com.sb.mediasearch.core.network.model.ImageResponse
import com.sb.mediasearch.core.network.model.VideoResponse
import java.util.UUID

internal fun VideoResponse.toContentData(): Content =
    Content(
        uuid = UUID.randomUUID().toString(),
        playTime = this.playTime,
        thumbnailUrl = this.thumbnail,
        url = this.url,
        datetime = this.datetime,
        type = "video"
    )

internal fun ImageResponse.toContentData(): Content =
    Content(
        uuid = UUID.randomUUID().toString(),
        playTime = null,
        thumbnailUrl = this.thumbnailUrl,
        url = this.imageUrl,
        datetime = this.datetime,
        type = "image"
    )