package com.xische.universities.feature.common.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class University(
    val name: String,
    val state: String?,
    val country: String,
    val countryCode: String,
    val webPage: String,
) : Parcelable
