package com.example.workhours

import android.app.Application
import android.content.SharedPreferences
import androidx.room.Room
import com.example.workhours.room.AppDatabase

class App : Application() {
    lateinit var db: AppDatabase
        private set

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
        db = Room
            .inMemoryDatabaseBuilder(
                this,
                AppDatabase::class.java


            ).build()
        pref = getSharedPreferences(PREF_NAME, MODE_PRIVATE)


    }

    fun saveToPreferences(key: String, value: Int) {
        val editor = pref.edit()
        editor.putInt(key, value)
        editor.apply()
    }

    companion object {
        lateinit var INSTANCE: App
            private set

        private lateinit var pref: SharedPreferences

        const val KEK_NAME = "work_hours"
        private const val PREF_NAME = "pref_work_hours"
    }
}