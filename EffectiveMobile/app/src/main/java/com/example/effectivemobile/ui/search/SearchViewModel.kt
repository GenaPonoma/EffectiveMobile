package com.example.effectivemobile.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.effectivemobile.data.model.Offer
import com.example.effectivemobile.data.model.Vacancy
import com.example.effectivemobile.repository.MainRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SearchViewModel(private val repository: MainRepository) : ViewModel() {

    private var _offers = MutableLiveData<List<Offer>>()
    val offers: LiveData<List<Offer>> get() = _offers
    private val _vacancies = MutableLiveData<List<Vacancy>>()
    val vacancycies: LiveData<List<Vacancy>> get() = _vacancies


    fun saveItemToDatabase(itemId: Int) {
        viewModelScope.launch {
//            repository.toggleFavorite(itemId.toString()) // Преобразуем Int в String, если требуется
        }
    }
    fun getOffers() {
        viewModelScope.launch {
            if (_offers.value == null) {
                // Загружаем данные только если они еще не были загружены
                val result =  repository.getData().offers
                _offers.postValue(result)
            }
        }
    }
    fun getVacancy() {
        viewModelScope.launch {
            if (_vacancies.value == null) {
                // Загружаем данные только если они еще не были загружены
                val result =  repository.getData().vacancies
                _vacancies.postValue(result)
            }
        }
    }


}

