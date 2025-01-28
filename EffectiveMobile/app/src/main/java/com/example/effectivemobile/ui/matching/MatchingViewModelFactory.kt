package com.example.effectivemobile.ui.matching

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.effectivemobile.repository.MainRepository

class MatchingViewModelFactory : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MatchingViewModel::class.java)) {
            return MatchingViewModel(MainRepository()) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")


    }
}