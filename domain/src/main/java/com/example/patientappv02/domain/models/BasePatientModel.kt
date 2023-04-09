package com.example.patientappv02.domain.models

import com.google.gson.annotations.SerializedName

class BasePatientModel<T> (
    @SerializedName("message")
    val message: String? = null,
    @SerializedName("status")
    val status: Int? = null,

    val data: T? = null

    )