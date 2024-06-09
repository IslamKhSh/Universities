package com.xische.universities.feature.universitieslist.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.xische.universities.feature.common.model.University

@Entity(tableName = "universities")
data class UniversityEntity(
    @PrimaryKey
    val name : String,
    val state : String?,
    val country : String,
    val countryCode : String,
    val webPage : String,
)

internal fun UniversityEntity.toDomainModel() = University(
    name = name,
    state = state,
    country= country,
    countryCode= countryCode,
    webPage = webPage
)