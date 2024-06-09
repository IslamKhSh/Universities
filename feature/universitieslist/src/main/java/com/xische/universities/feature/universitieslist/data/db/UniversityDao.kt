package com.xische.universities.feature.universitieslist.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UniversityDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUniversities(universities : List<UniversityEntity>)

    @Query("SELECT * FROM universities")
    suspend fun getAllUniversities(): List<UniversityEntity>
}