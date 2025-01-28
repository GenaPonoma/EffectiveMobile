package com.example.effectivemobile.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class Experience(

    @Json(name = "previewText")
    val previewText: String?,


    @Json(name = "text")
    val text: String?
)