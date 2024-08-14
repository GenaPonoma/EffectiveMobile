package com.example.workhours.ui.add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.workhours.ui.repository.MainRepository


class AddViewModelFactory(private val repository: MainRepository) : ViewModelProvider.Factory {


    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(AddViewModel::class.java))
            return AddViewModel(repository) as T
        throw IllegalArgumentException("Неверно ошибка: 22221232324343446554645")
    }


}