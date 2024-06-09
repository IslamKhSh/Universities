package com.xische.universities.feature.universitieslist.fixtures

import com.xische.universities.feature.common.model.University

val university = University(
    name = "University name",
    state = "University state",
    country = "Country",
    countryCode = "Country code",
    webPage = "web page"
)

val university2 = University(
    name = "University2 name",
    state = "University2 state",
    country = "Country2",
    countryCode = "Country2 code",
    webPage = "web page2"
)



val universitiesList = listOf(university, university2)