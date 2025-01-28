package com.example.effectivemobile.app

import android.app.Application
import androidx.room.Room
import com.example.effectivemobile.data.model.room.AppDatabase

lateinit var db: AppDatabase


class App : Application() {
    override fun onCreate() {
        super.onCreate()
        db = Room.databaseBuilder(
            this,
            AppDatabase::class.java,
            "app_database"
        ).build()
    }
}