package com.sb.mediasearch.core.data.repository

import com.sb.mediasearch.core.database.dao.ContentDao
import com.sb.mediasearch.core.database.model.ContentEntity
import com.sb.mediasearch.core.database.model.asExternalModel
import com.sb.mediasearch.core.model.Content
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import kotlinx.datetime.Clock
import javax.inject.Inject

internal class DefaultBookmarkRepository @Inject constructor(
    private val contentDao: ContentDao,
) : BookmarkRepository{
    override fun getBookmarks(): Flow<List<Content>> =
        contentDao.getBookmarkedContents().map { bookmarkedContents ->
            bookmarkedContents.map { it.asExternalModel() }
        }

    override suspend fun bookmarkContent(content: Content) = withContext(Dispatchers.IO) {
        if (content.isBookmarked) {
            contentDao.deleteBookmarkedContent(content.uuid)
        } else {
            contentDao.insertOrReplaceBookmarkedContent(
                ContentEntity(
                    uuid = content.uuid,
                    playTime = content.playTime,
                    thumbnailUrl = content.thumbnailUrl,
                    url = content.url,
                    datetime = Clock.System.now(),
                    type = content.type
                )
            )
        }
    }
}