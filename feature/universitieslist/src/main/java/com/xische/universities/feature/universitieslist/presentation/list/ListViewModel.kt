package com.xische.universities.feature.universitieslist.presentation.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.distinctUntilChanged
import androidx.lifecycle.viewModelScope
import com.xische.universities.feature.common.data.Result
import com.xische.universities.feature.universitieslist.domain.GetUniversitiesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val getUniversitiesUseCase: GetUniversitiesUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<UniversitiesListUiState>(UniversitiesListUiState.Loading)
    val uiState = _uiState.asLiveData().distinctUntilChanged()

    init {
        getUniversities()
    }

    fun getUniversities() {
        _uiState.value = UniversitiesListUiState.Loading
        viewModelScope.launch {
            _uiState.value = when (val result = getUniversitiesUseCase(Unit)) {
                is Result.Failure -> UniversitiesListUiState.Error(result.throwable)
                is Result.Success -> UniversitiesListUiState.Success(result.data)
            }
        }
    }

}