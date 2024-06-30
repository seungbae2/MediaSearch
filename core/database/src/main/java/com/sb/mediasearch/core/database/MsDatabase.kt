package com.sb.mediasearch.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.sb.mediasearch.core.database.dao.ContentDao
import com.sb.mediasearch.core.database.model.ContentEntity
import com.sb.mediasearch.core.database.util.InstantConverter

@Database(
    entities = [
        ContentEntity::class
    ],
    version = 1,
    exportSchema = false,
)
@TypeConverters(InstantConverter::class)
internal abstract class MsDatabase : RoomDatabase() {
    abstract fun contentDao(): ContentDao
}