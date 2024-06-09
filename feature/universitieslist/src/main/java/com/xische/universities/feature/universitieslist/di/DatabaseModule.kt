package com.xische.universities.feature.universitieslist.di

import android.content.Context
import androidx.room.Room
import com.xische.universities.feature.universitieslist.data.db.AppDatabase
import com.xische.universities.feature.universitieslist.data.db.UniversityDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "Universities.db"
        ).build()
    }

    @Provides
    fun provideUniversityDao(appDatabase: AppDatabase) = appDatabase.universityDao()
}