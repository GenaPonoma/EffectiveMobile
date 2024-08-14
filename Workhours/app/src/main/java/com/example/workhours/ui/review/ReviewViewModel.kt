package com.example.workhours.ui.review

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import com.example.workhours.ui.repository.MainRepository

class ReviewViewModel(private val repository: MainRepository) : ViewModel() {

    // Используем LiveData для наблюдения за изменениями в базе данных
    val monthlySummaries = repository.getAllMonthlySummaries().asFlow()


}


