package com.example.patientappv02.domain.usecase.details

import androidx.lifecycle.SavedStateHandle
import com.example.patientappv02.domain.models.patients.Data
import com.example.patientappv02.domain.repo.PatientRepo
import javax.inject.Inject

class DetailsPatientUseCase @Inject constructor(private val repo: PatientRepo) {
    suspend operator fun invoke(id:String):Data?{
        return repo.getPatient(id)
    }
}