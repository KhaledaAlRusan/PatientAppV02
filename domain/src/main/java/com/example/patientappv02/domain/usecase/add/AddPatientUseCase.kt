package com.example.patientappv02.domain.usecase.add

import com.example.patientappv02.domain.models.add.AddPatientRequest
import com.example.patientappv02.domain.models.patients.Data
import com.example.patientappv02.domain.repo.PatientRepo
import javax.inject.Inject

class AddPatientUseCase @Inject constructor(private val repo: PatientRepo) {
    suspend operator fun invoke(addPatientRequest: AddPatientRequest):Data?{
        return repo.postPatient(addPatientRequest)
    }
}