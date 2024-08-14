package com.example.workhours.ui.review

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.workhours.ui.repository.MainRepository

class ReviewViewModelFactory(private val repository: MainRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ReviewViewModel::class.java)){
            return ReviewViewModel(repository) as T
        }
        throw IllegalArgumentException("Ошибка создания ViewModel")
    }
}