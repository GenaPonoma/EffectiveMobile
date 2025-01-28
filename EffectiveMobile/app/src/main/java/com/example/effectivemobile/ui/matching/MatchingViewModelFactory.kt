package com.example.effectivemobile.ui.matching

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.effectivemobile.data.model.room.VacancyDao
import com.example.effectivemobile.repository.MainRepository

class MatchingViewModelFactory(private val dao: VacancyDao) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MatchingViewModel::class.java)) {
            return MatchingViewModel(MainRepository(dao)) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")


    }
}