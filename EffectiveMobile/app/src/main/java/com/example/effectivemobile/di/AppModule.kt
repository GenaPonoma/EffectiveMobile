package com.example.effectivemobile.di

import android.content.Context
import androidx.room.Room
import com.example.effectivemobile.data.model.room.AppDatabase
import com.example.effectivemobile.data.model.room.VacancyDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(val context: Context) {

    @Provides

    fun provideContext(): Context = context

    @Provides
    @Singleton
    fun provideDb(context: Context): AppDatabase {
        return Room.inMemoryDatabaseBuilder(
            context,
            AppDatabase::class.java
          //  "app_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideVacancyDao(db: AppDatabase): VacancyDao = db.vacancyDao()
}