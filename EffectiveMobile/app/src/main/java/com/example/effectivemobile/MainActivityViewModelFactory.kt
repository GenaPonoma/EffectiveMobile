package com.example.effectivemobile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.effectivemobile.repository.MainRepository
import javax.inject.Inject

class MainActivityViewModelFactory @Inject constructor(private val repository: MainRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainActivityViewModel::class.java)) {
            return MainActivityViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")


    }
}