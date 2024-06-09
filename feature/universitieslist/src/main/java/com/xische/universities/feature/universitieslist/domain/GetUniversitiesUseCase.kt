package com.xische.universities.feature.universitieslist.domain

import com.xische.universities.feature.common.data.Result
import com.xische.universities.feature.common.di.IoDispatcher
import com.xische.universities.feature.common.domain.BaseUseCase
import com.xische.universities.feature.common.model.University
import com.xische.universities.feature.universitieslist.domain.repos.UniversitiesRepo
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class GetUniversitiesUseCase @Inject constructor(
    @IoDispatcher dispatcher: CoroutineDispatcher,
    private val universitiesRepo: UniversitiesRepo
) : BaseUseCase<Unit, Result<List<University>>>(dispatcher) {

    override suspend fun run(params: Unit) = universitiesRepo.getUniversities()
}
