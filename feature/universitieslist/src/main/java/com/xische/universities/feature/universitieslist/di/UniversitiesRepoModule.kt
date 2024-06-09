package com.xische.universities.feature.universitieslist.di

import com.xische.universities.feature.universitieslist.data.UniversitiesRepoImp
import com.xische.universities.feature.universitieslist.domain.repos.UniversitiesRepo
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * binds the implementation of repositories (in data layer) to their abstractions (in domain layer)
 * so when a use case ask for an abstract repository hilt will provide the implementation of it
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class UniversitiesRepoModule {

    @Binds
    @Singleton
    abstract fun bindUniversitiesRepo(repo: UniversitiesRepoImp): UniversitiesRepo
}
