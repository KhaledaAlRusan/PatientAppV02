package com.example.patientappv02.domain.usecase.delete

import com.example.patientappv02.domain.models.delete.DeletePatientResponse
import com.example.patientappv02.domain.repo.PatientRepo
import javax.inject.Inject

class DeletePatientUseCase @Inject constructor(private val repo: PatientRepo) {
    suspend operator fun invoke(id:String):DeletePatientResponse{
        return repo.deletePatient(id)
    }
}