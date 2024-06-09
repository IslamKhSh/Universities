package com.xische.universities.feature.universitieslist.data.db

import com.xische.universities.feature.universitieslist.fixtures.university
import com.xische.universities.feature.universitieslist.fixtures.university2
import com.xische.universities.feature.universitieslist.fixtures.universityEntity
import com.xische.universities.feature.universitieslist.fixtures.universityEntity2
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class UniversityEntityKtTest{

    @Test
    fun `test mapping university entity to domain model`(){
        universityEntity.toDomainModel() shouldBeEqualTo university
    }

    @Test
    fun `test mapping university entity 2 to domain model`(){
        universityEntity2.toDomainModel() shouldBeEqualTo university2
    }
}