package com.example.patientappv02.domain.models.add

import com.google.gson.annotations.SerializedName

data class AddPatientRequest(
    @SerializedName("name")
    val name:String,
    @SerializedName("address")
    val address:String,
    @SerializedName("gender")
    val gender:String,
    @SerializedName("birthdate")
    val birthdate:String,
    @SerializedName("mobile")
    val mobile:String,
    @SerializedName("email")
    val email:String,
)
