package com.example.patientappv02.data.datasource

import com.example.patientappv02.domain.models.add.AddPatientRequest
import com.example.patientappv02.domain.models.patients.Data
import com.example.patientappv02.domain.models.patients.Patients
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface PatientDataSource {

    @GET("/patients")
    suspend fun getPatients(): Patients
    @POST("/patients")
    suspend fun postPatient(@Body addPatientRequest: AddPatientRequest): Data
}