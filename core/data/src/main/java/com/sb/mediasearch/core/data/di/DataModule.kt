package com.sb.mediasearch.core.data.di

import com.sb.mediasearch.core.data.repository.BookmarkRepository
import com.sb.mediasearch.core.data.repository.DefaultBookmarkRepository
import com.sb.mediasearch.core.data.repository.DefaultSearchContentsRepository
import com.sb.mediasearch.core.data.repository.SearchContentsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class DataModule {
    @Binds
    internal abstract fun bindsSearchContentsRepository(
        searchContentsRepository: DefaultSearchContentsRepository,
    ): SearchContentsRepository

    @Binds
    internal abstract fun bindsBookmarkRepository(
        bookmarkRepository: DefaultBookmarkRepository,
    ): BookmarkRepository
}