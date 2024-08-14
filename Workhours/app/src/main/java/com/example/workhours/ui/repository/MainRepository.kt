package com.example.workhours.ui.repository

import com.example.workhours.room.MonthlySummaryDao
import com.example.workhours.room.NewShift
import com.example.workhours.room.ShiftDao

class MainRepository(
    private val monthlySummaryDao: MonthlySummaryDao,
    private val shiftDao: ShiftDao,
    ) {

    suspend fun insertMonthlySummary(newShift: NewShift) = shiftDao.insertShiftAndUpdateSummary(newShift)
    fun getAllMonthlySummaries() = monthlySummaryDao.getAll()
}