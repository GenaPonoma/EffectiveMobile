package com.example.workhours.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import kotlinx.coroutines.flow.Flow


@Dao
interface ShiftDao {
    @Query("SELECT * FROM Shifts")
    fun getAll(): Flow<List<Shift>>

    @Insert(entity = Shift::class)
    suspend fun insertShift(shift: NewShift)

    @Delete
    suspend fun deleteShift(shift: Shift)

    @Update
    suspend fun updateShift(shift: Shift)

    @Query("SELECT * FROM MonthlySummary WHERE year = :year AND month = :month LIMIT 1")
    suspend fun getMonthlySummary(year: Int, month: String): MonthlySummary?

    @Update
    suspend fun updateMonthlySummary(summary: MonthlySummary)

    @Insert(entity = MonthlySummary::class)
    suspend fun insertMonthlySummary(summary: NewMonthlySummary)

    fun calculateHours(startTime: String, endTime: String): Double {
//        // Здесь вы можете добавить свою логику для расчета количества часов
//        // Например, если startTime и endTime представляют собой строки в формате "HH:mm":
//        val start = LocalTime.parse(startTime)
//        val end = LocalTime.parse(endTime)
//        val duration = Duration.between(start, end)
        return 0.0
    }


    @Transaction
    suspend fun insertShiftAndUpdateSummary(shift: NewShift): Unit? {
        insertShift(shift)
        // Получаем обобщенную информацию для этого месяца
        val summary = getMonthlySummary(shift.year, shift.month)

        if (summary != null) {
            // Если обобщенная информация уже существует, обновляем ее
            summary.shiftCount++
            summary.totalHours += calculateHours(shift.startTime, shift.endTime) ?: 0.0
            summary.totalSalary += shift.salary
            updateMonthlySummary(summary)
        } else {
            // Если обобщенной информации еще нет, создаем новую
            calculateHours(shift.startTime, shift.endTime).let {
                val newSummary = NewMonthlySummary(
                    year = shift.year,
                    month = shift.month,
                    shiftCount = 1,
                    totalHours = it,
                    totalSalary = shift.salary
                )
                insertMonthlySummary(newSummary)
            }
        }
        return null
    }


}