package com.xische.universities.feature.universitieslist.domain.repos

import com.xische.universities.feature.common.model.University
import com.xische.universities.feature.common.data.Result

interface UniversitiesRepo {

    suspend fun getUniversities(): Result<List<University>>
}
