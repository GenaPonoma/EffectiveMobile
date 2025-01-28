package com.example.effectivemobile.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class Address(
    @ColumnInfo(name = "house")
    @Json(name = "house")
    val house: String?,
    @ColumnInfo(name = "street")
    @Json(name = "street")
    val street: String?,
    @ColumnInfo(name = "town")
    @Json(name = "town")
    val town: String?
)