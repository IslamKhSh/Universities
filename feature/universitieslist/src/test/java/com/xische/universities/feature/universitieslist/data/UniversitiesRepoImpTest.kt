package com.xische.universities.feature.universitieslist.data

import com.xische.universities.feature.common.data.Result
import com.xische.universities.feature.common.data.data
import com.xische.universities.feature.universitieslist.data.db.UniversityDao
import com.xische.universities.feature.universitieslist.data.networking.UniversitiesApiService
import com.xische.universities.feature.universitieslist.data.networking.toEntityModel
import com.xische.universities.feature.universitieslist.fixtures.universitiesDtoList
import com.xische.universities.feature.universitieslist.fixtures.universitiesEntitiesList
import com.xische.universities.feature.universitieslist.fixtures.universitiesList
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import kotlinx.coroutines.runBlocking
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldBeInstanceOf
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class UniversitiesRepoImpTest {

    @MockK(relaxed = true)
    private lateinit var apiService: UniversitiesApiService

    @MockK(relaxed = true)
    private lateinit var universityDao: UniversityDao

    private lateinit var repo: UniversitiesRepoImp

    @BeforeEach
    fun setUp() {
        repo = UniversitiesRepoImp(apiService, universityDao)
    }

    @Test
    fun `test cashing data after success api call`() {
        // given
        coEvery { apiService.getUniversities() } returns universitiesDtoList

        // when
        val result = runBlocking {
            repo.getUniversities()
        }

        // then
        coVerify { universityDao.insertUniversities(universitiesDtoList.map { it.toEntityModel() }) }
        result shouldBeInstanceOf Result.Success::class
    }

    @Test
    fun `test returning data from api call when success`() {
        // given
        coEvery { apiService.getUniversities() } returns universitiesDtoList

        // when
        val result = runBlocking {
            repo.getUniversities()
        }

        // then
        result shouldBeInstanceOf Result.Success::class
        result.data shouldBeEqualTo universitiesList
    }

    @Test
    fun `test returning data from cashing after failed api call`() {
        // given
        coEvery { apiService.getUniversities() } throws Throwable()
        coEvery { universityDao.getAllUniversities() } returns universitiesEntitiesList

        // when
        val result = runBlocking {
            repo.getUniversities()
        }

        // then
        result shouldBeInstanceOf Result.Success::class
        result.data shouldBeEqualTo universitiesList
    }

    @Test
    fun `test returning error when api call failed and no cashed data`() {
        // given
        coEvery { apiService.getUniversities() } throws Throwable()
        coEvery { universityDao.getAllUniversities() } returns emptyList()

        // when
        val result = runBlocking {
            repo.getUniversities()
        }

        // then
        result shouldBeInstanceOf Result.Failure::class
    }
}