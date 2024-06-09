package com.xische.universities.feature.common.data

import com.xische.universities.feature.common.data.Result.Success

sealed class Result<out R> {
    data class Success<out T>(val data: T) : Result<T>()
    data class Failure(val throwable: Throwable? = null) : Result<Nothing>()
}

/**
 * `true` if [Result] is of type [Success] & holds non-null [Success.data].
 */
val Result<*>.isSucceeded
    get() = this is Success && data != null

val <T> Result<T>.data: T
    get() = (this as Success).data!!

