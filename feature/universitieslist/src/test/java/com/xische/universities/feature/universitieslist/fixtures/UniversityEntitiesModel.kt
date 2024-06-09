package com.xische.universities.feature.universitieslist.fixtures

import com.xische.universities.feature.common.model.University
import com.xische.universities.feature.universitieslist.data.db.UniversityEntity

val universityEntity = UniversityEntity(
    name = "University name",
    state = "University state",
    country = "Country",
    countryCode = "Country code",
    webPage = "web page"
)

val universityEntity2 = UniversityEntity(
    name = "University2 name",
    state = "University2 state",
    country = "Country2",
    countryCode = "Country2 code",
    webPage = "web page2"
)



val universitiesEntitiesList = listOf(universityEntity, universityEntity2)