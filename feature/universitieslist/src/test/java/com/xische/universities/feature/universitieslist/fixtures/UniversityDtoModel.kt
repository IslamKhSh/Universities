package com.xische.universities.feature.universitieslist.fixtures

import com.xische.universities.feature.common.model.University
import com.xische.universities.feature.universitieslist.data.db.UniversityEntity
import com.xische.universities.feature.universitieslist.data.networking.UniversityDto

val universityDto = UniversityDto(
    name = "University name",
    stateProvince = "University state",
    country = "Country",
    alphaTwoCode = "Country code",
    webPages = listOf("web page"),
    domains = listOf("domain")
)

val universityDto2 = UniversityDto(
    name = "University2 name",
    stateProvince = "University2 state",
    country = "Country2",
    alphaTwoCode = "Country2 code",
    webPages = listOf("web page2"),
    domains = listOf("domain 2")
)

val universitiesDtoList = listOf(universityDto, universityDto2)