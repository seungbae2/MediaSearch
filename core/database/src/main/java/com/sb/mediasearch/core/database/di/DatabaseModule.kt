package com.sb.mediasearch.core.database.di

import android.content.Context
import androidx.room.Room
import com.sb.mediasearch.core.database.MsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object DatabaseModule {
    @Provides
    @Singleton
    fun providesMsDatabase(
        @ApplicationContext context: Context,
    ): MsDatabase = Room.databaseBuilder(
        context,
        MsDatabase::class.java,
        "ms_database",
    ).build()
}