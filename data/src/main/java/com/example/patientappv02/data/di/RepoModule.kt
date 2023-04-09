package com.example.patientappv02.data.di

import com.example.patientappv02.data.datasource.PatientDataSource
import com.example.patientappv02.data.repoImpl.PatientRepoImpl
import com.example.patientappv02.domain.repo.PatientRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepoModule {

    @Provides
    fun provideRepoPatient(patientDataSource:PatientDataSource):PatientRepo{
        return PatientRepoImpl(patientDataSource)
    }
}