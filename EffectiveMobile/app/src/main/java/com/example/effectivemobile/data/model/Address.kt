package com.example.effectivemobile.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Address(

    @Json(name = "house")
    val house: String?,
    @Json(name = "street")
    val street: String?,
    @Json(name = "town")
    val town: String?
)