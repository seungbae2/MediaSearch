package com.sb.mediasearch.core.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.sb.mediasearch.core.database.model.ContentEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ContentDao {

    @Upsert
    suspend fun insertOrReplaceBookmarkedContent(content: ContentEntity)

    @Query(value = "SELECT * FROM contents ORDER BY datetime ASC")
    fun getBookmarkedContents(): Flow<List<ContentEntity>>

    @Query(value = "DELETE FROM contents WHERE uuid = :uuid")
    fun deleteBookmarkedContent(uuid: String)

    @Query(value = "DELETE FROM contents")
    suspend fun deleteAllBookmarks()
}