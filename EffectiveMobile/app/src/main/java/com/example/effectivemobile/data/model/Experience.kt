package com.example.effectivemobile.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@Entity
@JsonClass(generateAdapter = true)
data class Experience(
    @ColumnInfo(name = "previewText")
    @Json(name = "previewText")
    var previewText: String?,
    @ColumnInfo(name = "text")
    @Json(name = "text")
    var text: String?
)