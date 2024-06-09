package com.xische.universities.feature.universitieslist.presentation.list

import com.xische.universities.feature.common.model.University

sealed class UniversitiesListUiState {
    data object Loading : UniversitiesListUiState()
    data class Success(val data: List<University>) : UniversitiesListUiState()
    data class Error(val throwable: Throwable?) : UniversitiesListUiState()
}