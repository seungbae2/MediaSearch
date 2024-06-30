package com.sb.mediasearch.core.database.di

import com.sb.mediasearch.core.database.MsDatabase
import com.sb.mediasearch.core.database.dao.ContentDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal object DaoModule {
    @Provides
    fun providesContentDao(database: MsDatabase): ContentDao = database.contentDao()
}