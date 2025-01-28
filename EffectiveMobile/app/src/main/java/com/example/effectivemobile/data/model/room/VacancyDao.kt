package com.example.effectivemobile.data.model.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.effectivemobile.data.model.Vacancy

@Dao
interface VacancyDao {

    @Query("SELECT * FROM vacancy WHERE id = :id")
    suspend fun getVacancyById(id: String): Vacancy?

    @Insert
    suspend fun insert(vacancy: Vacancy)
    @Update
    suspend fun update(vacancy: Vacancy)

    @Query("SELECT * FROM vacancy WHERE isFavorite = 1")
    fun getFavorites(): LiveData<List<Vacancy>>
}