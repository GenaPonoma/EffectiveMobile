package com.example.workhours.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Shift::class, MonthlySummary::class], version = 1)

abstract class AppDatabase : RoomDatabase() {
    abstract fun dateTimeUserDao(): ShiftDao
    abstract fun monthlySummaryDao(): MonthlySummaryDao
}