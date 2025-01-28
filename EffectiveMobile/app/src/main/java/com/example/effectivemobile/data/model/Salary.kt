package com.example.effectivemobile.data.model


import androidx.room.ColumnInfo
import androidx.room.Entity
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class Salary (
    @ColumnInfo(name = "short")
    @Json(name = "short")
    var short: String? = null,
    @ColumnInfo(name = "full")
    @Json(name = "full")
    var full: String? = null
) {
    // Пустой конструктор нужен для работы с Room
    constructor() : this(null, null)
}
