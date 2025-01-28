package com.example.effectivemobile.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.effectivemobile.repository.MainRepository

class SearchViewModelFactory :  ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SearchViewModel::class.java)) {
            return SearchViewModel(MainRepository()) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")


    }


}