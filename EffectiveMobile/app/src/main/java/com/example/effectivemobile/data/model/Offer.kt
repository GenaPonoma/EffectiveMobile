package com.example.effectivemobile.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Offer(
    @Json(name = "button")
    var button: Button?,
    @Json(name = "id")
    var id: String?,
    @Json(name = "link")
    var link: String?,
    @Json(name = "title")
    var title: String?
)