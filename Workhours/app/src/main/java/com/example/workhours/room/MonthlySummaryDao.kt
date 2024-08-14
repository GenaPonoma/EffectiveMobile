package com.example.workhours.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface MonthlySummaryDao {

    @Query("SELECT * FROM MonthlySummary")
    fun getAll(): LiveData<List<MonthlySummary>>
    @Insert(entity = MonthlySummary::class)
    suspend fun insertMonthlySummary(monthlySummary: NewMonthlySummary)
}