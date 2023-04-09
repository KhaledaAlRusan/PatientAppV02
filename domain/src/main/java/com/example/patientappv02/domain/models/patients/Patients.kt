package com.example.patientappv02.domain.models.patients


import com.google.gson.annotations.SerializedName

data class Patients(
    @SerializedName("data")
    val `data`: List<Data?>? = null,
    @SerializedName("message")
    val message: String? = null,
    @SerializedName("status")
    val status: Int? = null
)