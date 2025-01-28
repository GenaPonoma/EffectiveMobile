package com.example.effectivemobile.data.model.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.effectivemobile.data.model.Vacancy


@Database(entities = [Vacancy::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun vacancyDao(): VacancyDao

}