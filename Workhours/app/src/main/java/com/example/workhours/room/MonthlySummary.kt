package com.example.workhours.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "MonthlySummary")
data class MonthlySummary(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "year")
    val year: Int,
    @ColumnInfo(name = "month")
    val month: String,
    @ColumnInfo(name = "shift_count")
    var shiftCount: Int,
    @ColumnInfo(name = "total_hours")
    var totalHours: Double,
    @ColumnInfo(name = "total_salary")
    var totalSalary: Double
)
