package com.xische.universities.feature.universitieslist.presentation.list

import com.xische.universities.feature.common.data.Result
import com.xische.universities.feature.universitieslist.domain.GetUniversitiesUseCase
import com.xische.universities.feature.universitieslist.fixtures.universitiesList
import com.xische.universities.feature.universitieslist.utils.createMockedObserver
import com.xische.universities.feature.universitieslist.utils.extenions.CoroutinesTestDispatcherExtension
import com.xische.universities.feature.universitieslist.utils.extenions.InstantTaskExecutorExtension
import com.xische.universities.feature.universitieslist.utils.getLiveDataChanges
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@OptIn(ExperimentalCoroutinesApi::class)
@ExtendWith(
    InstantTaskExecutorExtension::class,
    CoroutinesTestDispatcherExtension::class,
    MockKExtension::class
)
class ListViewModelTest {

    @MockK
    private lateinit var getUniversitiesUseCase: GetUniversitiesUseCase

    private lateinit var viewModel: ListViewModel

    private val stateObserver = createMockedObserver<UniversitiesListUiState>()

    @BeforeEach
    fun setup() {
        viewModel = ListViewModel(getUniversitiesUseCase)
        viewModel.uiState.observeForever(stateObserver)
    }

    @Test
    fun `when call getUniversities use case must be called`() {
        // given
        coEvery { getUniversitiesUseCase(Unit) } returns Result.Success(universitiesList)

        // when
        runTest { viewModel.getUniversities() }

        // then
        coVerify { getUniversitiesUseCase(Unit) }
    }

    @Test
    fun `when getUniversities returns success the uiState must be loading then success`() {
        // given
        coEvery { getUniversitiesUseCase(Unit) } returns Result.Success(universitiesList)

        // when
        runTest { viewModel } // init block calls getUniversities

        // then
        getLiveDataChanges(stateObserver) shouldBeEqualTo listOf(
            UniversitiesListUiState.Loading,
            UniversitiesListUiState.Success(universitiesList)
        )
    }

    @Test
    fun `when getUniversities returns failure the uiState must be loading then error`() {
        // given
        val throwable = Throwable()
        coEvery { getUniversitiesUseCase(Unit) } returns Result.Failure(throwable)

        // when
        runTest { viewModel } // init block calls getUniversities

        // then
        getLiveDataChanges(stateObserver) shouldBeEqualTo listOf(
            UniversitiesListUiState.Loading,
            UniversitiesListUiState.Error(throwable)
        )
    }
}