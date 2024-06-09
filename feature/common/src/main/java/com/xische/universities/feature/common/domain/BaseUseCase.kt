package com.xische.universities.feature.common.domain

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

abstract class BaseUseCase<in Params, out R>(
    private val coroutineDispatcher: CoroutineDispatcher,
) {
    suspend operator fun invoke(params: Params): R = withContext(coroutineDispatcher) {
        run(params)
    }

    abstract suspend fun run(params: Params): R
}