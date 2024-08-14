package com.example.workhours.ui.add

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.workhours.room.NewShift
import com.example.workhours.ui.repository.MainRepository
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar

class AddViewModel(
    private val repository: MainRepository

) : ViewModel() {
    lateinit var month: String
    val calendar = Calendar.getInstance()
    lateinit var datePickerStart: MaterialDatePicker<Long>
    lateinit var datePickerEnd: MaterialDatePicker<Long>
    lateinit var pickerTimeStart: MaterialTimePicker
    lateinit var pickerTimeEnd: MaterialTimePicker

    @SuppressLint("SimpleDateFormat")
    private val dateFormat = SimpleDateFormat("dd MMMM")

    @SuppressLint("SimpleDateFormat")
    private val dateFormatMonth = SimpleDateFormat("MMMM")

    @SuppressLint("SimpleDateFormat")
    private val timeFormat = SimpleDateFormat("HH:mm")


    fun getFormattedDate(timeInMillis: Long): String {
        calendar.timeInMillis = timeInMillis
        return dateFormat.format(calendar.timeInMillis)
    }

    fun getFormattedTime(hour: Int, minute: Int): String {
        calendar.set(Calendar.HOUR, hour)
        calendar.set(Calendar.MINUTE, minute)
        return timeFormat.format(calendar.time)
    }

    fun getMonthFromDate(timeInMillis: Long): String {
        calendar.timeInMillis = timeInMillis
        val monthIndex = calendar.get(Calendar.MONTH) // Получаем индекс месяца (0-11)
        // Массив месяцев на русском языке
        val months = arrayOf("Январь", "Февраль", "Март", "Апрель", "Май", "Июнь", "Июль", "Август", "Сентабрь", "Октябрь", "Ноябрь", "Декабрь")

        return months[monthIndex] // Возвращаем месяц на русском языке
    }

    fun createTimePicker(): MaterialTimePicker {
        return MaterialTimePicker.Builder()
            .setTimeFormat(TimeFormat.CLOCK_24H)
            .build()
    }


    fun showDatePicker(
        titleText: String,
        onDateSelected: (Long) -> Unit
    ): MaterialDatePicker<Long> {
        val dateTimePicker = MaterialDatePicker.Builder.datePicker()
            .setTitleText(titleText)
            .build()
        dateTimePicker.addOnPositiveButtonClickListener { timeInMillis ->
            onDateSelected(timeInMillis)
        }
        return dateTimePicker

    }

    fun addItemBD(
        month: String,
        startTime: String,
        endTime: String,
        startDate: String,
        endDate: String,
        comments: String
    ) {
        viewModelScope.launch {
            repository.insertMonthlySummary(
                NewShift(
                    year = 2024,
                    month = month,
                    startDate = startDate,
                    endDate = endDate,
                    startTime = startTime,
                    endTime = endTime,
                    comment = comments,
                    hourlyRate = 0.0,
                    salary = 22.0


                )
            )


        }


    }


}