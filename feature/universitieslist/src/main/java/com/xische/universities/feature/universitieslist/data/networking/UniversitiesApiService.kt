package com.xische.universities.feature.universitieslist.data.networking

import retrofit2.http.GET
import retrofit2.http.Query

interface UniversitiesApiService {

    @GET("search")
    suspend fun getUniversities(
        @Query("country") country: String = "United Arab Emirates"
    ): List<UniversityDto>
}
