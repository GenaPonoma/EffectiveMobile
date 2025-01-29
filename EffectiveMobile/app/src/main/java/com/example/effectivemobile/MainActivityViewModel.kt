package com.example.effectivemobile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import com.example.effectivemobile.data.model.Vacancy
import com.example.effectivemobile.repository.MainRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainActivityViewModel @Inject constructor(private val repository: MainRepository) :
    ViewModel() {
    private val _result = MutableLiveData<Int>()
    val result: LiveData<Int> get() = _result

    fun getVacancy() {
        viewModelScope.launch {
            if (_result.value == null) {
                // Загружаем данные только если они еще не были загружены
                repository.getFavorites().asFlow().collect { it ->

                    _result.value = it.size
                }

            }
        }
    }


}