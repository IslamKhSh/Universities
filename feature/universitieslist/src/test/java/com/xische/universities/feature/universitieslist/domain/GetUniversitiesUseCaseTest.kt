package com.xische.universities.feature.universitieslist.domain

import com.xische.universities.feature.common.data.Result
import com.xische.universities.feature.common.data.data
import com.xische.universities.feature.universitieslist.domain.repos.UniversitiesRepo
import com.xische.universities.feature.universitieslist.fixtures.universitiesList
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.StandardTestDispatcher
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldBeInstanceOf
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class GetUniversitiesUseCaseTest {

    @MockK(relaxed = true)
    lateinit var repo: UniversitiesRepo

    private lateinit var useCase: GetUniversitiesUseCase

    @BeforeEach
    fun setUp() {
        useCase = GetUniversitiesUseCase(StandardTestDispatcher(), repo)
    }

    @Test
    fun `test useCase fetch correct data from repo`() {
        // given
        coEvery { repo.getUniversities() } returns Result.Success(universitiesList)

        // when
        val result = runBlocking { useCase(Unit) }

        // then
        coVerify { repo.getUniversities() }
        result shouldBeInstanceOf Result.Success::class
        result.data shouldBeEqualTo universitiesList
    }
}