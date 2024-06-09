package com.xische.universities.feature.universitieslist.data.networking

import com.xische.universities.feature.universitieslist.data.db.toDomainModel
import com.xische.universities.feature.universitieslist.fixtures.university
import com.xische.universities.feature.universitieslist.fixtures.university2
import com.xische.universities.feature.universitieslist.fixtures.universityDto
import com.xische.universities.feature.universitieslist.fixtures.universityDto2
import com.xische.universities.feature.universitieslist.fixtures.universityEntity
import com.xische.universities.feature.universitieslist.fixtures.universityEntity2
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class UniversityDtoKtTest{

    @Test
    fun `test mapping university dto to domain model`(){
        universityDto.toDomainModel() shouldBeEqualTo university
    }

    @Test
    fun `test mapping university dto 2 to domain model`(){
        universityDto2.toDomainModel() shouldBeEqualTo university2
    }

    @Test
    fun `test mapping university dto to entity model`(){
        universityDto.toEntityModel() shouldBeEqualTo universityEntity
    }

    @Test
    fun `test mapping university dto 2 to entity model`(){
        universityDto2.toEntityModel() shouldBeEqualTo universityEntity2
    }
}