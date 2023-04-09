package com.example.patientappv02.domain.usecase.patients

import com.example.patientappv02.domain.models.patients.Data
import com.example.patientappv02.domain.repo.PatientRepo
import javax.inject.Inject

class GetPatientUseCase @Inject constructor(private val repo: PatientRepo) {

    suspend operator fun invoke():List<Data?>?{
        return repo.getPatients()?.sortedBy { it?.name }
    }
}