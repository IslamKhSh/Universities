package com.xische.universities.feature.universitieslist.data.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [UniversityEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun universityDao(): UniversityDao
}