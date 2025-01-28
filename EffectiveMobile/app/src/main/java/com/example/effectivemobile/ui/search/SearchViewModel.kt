package com.example.effectivemobile.ui.search

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import com.example.effectivemobile.data.model.Offer
import com.example.effectivemobile.data.model.Vacancy
import com.example.effectivemobile.repository.MainRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.forEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SearchViewModel(private val repository: MainRepository) : ViewModel() {

    private var _offers = MutableLiveData<List<Offer>>()
    val offers: LiveData<List<Offer>> get() = _offers
    private val _vacancies = MutableLiveData<List<Vacancy>>()
    val vacancycies: LiveData<List<Vacancy>> get() = _vacancies



    fun insertItemToDatabase() {
        viewModelScope.launch(Dispatchers.IO) {
            vacancycies.asFlow().collect {
                Log.d("toggleFavorite", "toggleFavorite: ${vacancycies.value}")
                it.forEach {
                    repository.insertVacancy(it)
                }
            }
        }
    }

    fun saveItemToDatabase(itemId: String) {
        viewModelScope.launch {
            repository.toggleFavorite(itemId)
        }
    }

    fun getOffers() {
        viewModelScope.launch {
            if (_offers.value == null) {
                // Загружаем данные только если они еще не были загружены
                val result = repository.getData().offers
                _offers.postValue(result)
            }
        }
    }

    fun getVacancy() {
        viewModelScope.launch {
            if (_vacancies.value == null) {
                // Загружаем данные только если они еще не были загружены
                val result = repository.getData().vacancies
                _vacancies.postValue(result)
            }
        }
    }


}

