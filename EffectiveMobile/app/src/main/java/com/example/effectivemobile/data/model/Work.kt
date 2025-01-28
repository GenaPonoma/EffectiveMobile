package com.example.effectivemobile.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Work(
    @Json(name = "offers")
    val offers: List<Offer>,
    @Json(name = "vacancies")
    val vacancies: List<Vacancy>
)