package com.example.effectivemobile.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.effectivemobile.data.model.room.VacancyDao
import com.example.effectivemobile.repository.MainRepository

class SearchViewModelFactory(private val dao: VacancyDao) :  ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SearchViewModel::class.java)) {
            return SearchViewModel(MainRepository(dao)) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")


    }


}