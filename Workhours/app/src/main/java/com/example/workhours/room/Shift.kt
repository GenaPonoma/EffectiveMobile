package com.example.workhours.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Shifts")
data class Shift(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "year")
    val year: Int,
    @ColumnInfo(name = "month")
    val month: String,
    @ColumnInfo(name = "startDate")
    val startDate: String,
    @ColumnInfo(name = "endDate")
    val endDate: String,
    @ColumnInfo(name = "start_time")
    val startTime: String,
    @ColumnInfo(name = "end_time")
    val endTime: String,
    @ColumnInfo(name = "comment")
    val comment: String,
    @ColumnInfo(name = "hourly_rate")
    val hourlyRate: Double,
    @ColumnInfo(name = "salary")
    val salary: Double
)