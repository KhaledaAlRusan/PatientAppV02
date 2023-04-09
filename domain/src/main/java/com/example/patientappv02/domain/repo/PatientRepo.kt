package com.example.patientappv02.domain.repo

import com.example.patientappv02.domain.models.patients.Data

interface PatientRepo {

    suspend fun getPatients():List<Data?>?
}