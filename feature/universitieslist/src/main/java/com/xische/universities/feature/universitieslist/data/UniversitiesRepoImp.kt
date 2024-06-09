package com.xische.universities.feature.universitieslist.data

import com.xische.universities.feature.common.model.University
import com.xische.universities.feature.universitieslist.data.db.UniversityDao
import com.xische.universities.feature.universitieslist.data.db.toDomainModel
import com.xische.universities.feature.universitieslist.data.networking.UniversitiesApiService
import com.xische.universities.feature.universitieslist.data.networking.toDomainModel
import com.xische.universities.feature.universitieslist.data.networking.toEntityModel
import com.xische.universities.feature.universitieslist.domain.repos.UniversitiesRepo
import com.xische.universities.feature.common.data.Result
import javax.inject.Inject

class UniversitiesRepoImp @Inject constructor(
    private val apiService: UniversitiesApiService,
    private val universityDao: UniversityDao
) : UniversitiesRepo {

    override suspend fun getUniversities(): Result<List<University>> {
        return try {
            val apiResult = apiService.getUniversities()
            universityDao.insertUniversities(apiResult.map { it.toEntityModel() })
            Result.Success(apiResult.map { it.toDomainModel() })
        } catch (t: Throwable) {
            val cashedItems = universityDao.getAllUniversities()

            if (cashedItems.isEmpty()) {
                Result.Failure(t)
            } else {
                Result.Success(cashedItems.map { it.toDomainModel() })
            }
        }
    }
}
