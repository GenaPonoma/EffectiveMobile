package com.example.effectivemobile.ui.matching

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.effectivemobile.repository.MainRepository
import javax.inject.Inject

class MatchingViewModelFactory @Inject constructor(private val repository: MainRepository) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MatchingViewModel::class.java)) {
            return MatchingViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")


    }
}