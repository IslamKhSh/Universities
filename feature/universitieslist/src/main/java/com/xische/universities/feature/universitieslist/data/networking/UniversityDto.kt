package com.xische.universities.feature.universitieslist.data.networking

import com.xische.universities.feature.common.model.University
import com.xische.universities.feature.universitieslist.data.db.UniversityEntity
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UniversityDto(
    @SerialName("alpha_two_code")
    val alphaTwoCode: String,
    @SerialName("domains")
    val domains: List<String>,
    @SerialName("name")
    val name: String,
    @SerialName("web_pages")
    val webPages: List<String>,
    @SerialName("country")
    val country: String,
    @SerialName("state-province")
    val stateProvince: String?
)

internal fun UniversityDto.toEntityModel() = UniversityEntity(
    name = name,
    state = stateProvince,
    country= country,
    countryCode= alphaTwoCode,
    webPage = webPages.first()
)

internal fun UniversityDto.toDomainModel() = University(
    name = name,
    state = stateProvince,
    country= country,
    countryCode= alphaTwoCode,
    webPage = webPages.first()
)
