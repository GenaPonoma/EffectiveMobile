package com.example.effectivemobile.data.model


import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
@Entity(tableName = "vacancy")
data class Vacancy constructor(
    @PrimaryKey(autoGenerate = true)
    var idN: Int?,
    @Embedded(prefix = "address_")
    @Json(name = "address")
    var address: Address?,
    @ColumnInfo(name = "appliedNumber")
    @Json(name = "appliedNumber")
    var appliedNumber: Int?,
    @ColumnInfo(name = "company")
    @Json(name = "company")

    var company: String?,
    @ColumnInfo(name = "description")
    @Json(name = "description")
    var description: String?,
    @Embedded(prefix = "experience_")
    @Json(name = "experience")
    var experience: Experience?,
    @ColumnInfo(name = "id")
    @Json(name = "id")
    var id: String?,
    @ColumnInfo(name = "isFavorite")
    @Json(name = "isFavorite")
    var isFavorite: Boolean?,
    @ColumnInfo(name = "lookingNumber")
    @Json(name = "lookingNumber")
    var lookingNumber: Int?,
    @ColumnInfo(name = "publishedDate")
    @Json(name = "publishedDate")
    var publishedDate: String?,
    @Ignore
    @Json(name = "questions")
    var questions: List<String>?,
    @ColumnInfo(name = "responsibilities")
    @Json(name = "responsibilities")
    var responsibilities: String?,

    @Json(name = "salary")
    @Embedded(prefix = "salary_")
    var salary: Salary?,
    @Ignore
    @Json(name = "schedules")
    var schedules: List<String>?,
    @ColumnInfo(name = "title")
    @Json(name = "title")
    var title: String?


){
    // Пустой конструктор для Room
    constructor() : this(
        0,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null
    )
}
