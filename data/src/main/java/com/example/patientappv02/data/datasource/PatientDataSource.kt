package com.example.patientappv02.data.datasource

import com.example.patientappv02.domain.models.patients.Patients
import retrofit2.http.GET

interface PatientDataSource {

    @GET("/patients")
    suspend fun getPatients(): Patients
}