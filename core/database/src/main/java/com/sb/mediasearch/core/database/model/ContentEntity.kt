package com.sb.mediasearch.core.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.sb.mediasearch.core.model.Content
import kotlinx.datetime.Instant
import java.util.UUID

@Entity(
    tableName = "contents",
)
data class ContentEntity(
    @PrimaryKey(autoGenerate = false)
    val uuid: String,
    val playTime: Int?,
    val thumbnailUrl: String,
    val url: String,
    val datetime: Instant,
    val type: String,
)

fun ContentEntity.asExternalModel() = Content(
    uuid = uuid,
    playTime = playTime,
    thumbnailUrl = thumbnailUrl,
    url = url,
    datetime = datetime,
    type = type
)