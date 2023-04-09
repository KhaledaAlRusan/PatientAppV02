package com.example.patientappv02.domain.models.patients


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("address")
    val address: String? = null,
    @SerializedName("birthdate")
    val birthdate: String? = null,
    @SerializedName("condition")
    val condition: String? = null,
    @SerializedName("createdAt")
    val createdAt: String? = null,
    @SerializedName("email")
    val email: String? = null,
    @SerializedName("gender")
    val gender: String? = null,
    @SerializedName("_id")
    val id: String? = null,
    @SerializedName("mobile")
    val mobile: String? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("photo")
    val photo: String? = null,
    @SerializedName("tests")
    val tests: List<Any?>? = null,
    @SerializedName("updatedAt")
    val updatedAt: String? = null,
    @SerializedName("__v")
    val v: Int? = null,


    //Local var
    var selected:Boolean = false
)