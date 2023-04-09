package com.example.patientappv02.data.repoImpl

import com.example.patientappv02.data.datasource.PatientDataSource
import com.example.patientappv02.domain.models.add.AddPatientRequest
import com.example.patientappv02.domain.models.patients.Data
import com.example.patientappv02.domain.repo.PatientRepo
import javax.inject.Inject

class PatientRepoImpl @Inject constructor(private val patientDataSource: PatientDataSource):PatientRepo {
    override suspend fun getPatients(): List<Data?>? {
        return patientDataSource.getPatients().data
    }

    override suspend fun postPatient(addPatientRequest: AddPatientRequest): Data{
        return patientDataSource.postPatient(addPatientRequest)
    }
}