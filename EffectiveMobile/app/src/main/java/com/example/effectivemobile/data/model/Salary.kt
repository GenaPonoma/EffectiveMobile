package com.example.effectivemobile.data.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Salary (

    @Json(name = "short")
    val short: String?,


    @Json(name = "full")
    val full: String?

)
