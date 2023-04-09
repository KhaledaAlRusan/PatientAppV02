package com.example.patientappv02.data.datasource

import com.example.patientappv02.domain.models.BasePatientModel
import com.example.patientappv02.domain.models.add.AddPatientRequest
import com.example.patientappv02.domain.models.delete.DeletePatientResponse
import com.example.patientappv02.domain.models.patients.Data
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface PatientDataSource {

    @GET("/patients")
    suspend fun getPatients(): BasePatientModel<List<Data?>?>

    @POST("/patients")
    suspend fun postPatient(@Body addPatientRequest: AddPatientRequest): Data

    @DELETE("/patients/{id}")
    suspend fun deletePatient(@Path("id") id:String): DeletePatientResponse

    @GET("/patients/{id}")
    suspend fun getPatient(@Path("id") id:String):BasePatientModel<Data?>
}