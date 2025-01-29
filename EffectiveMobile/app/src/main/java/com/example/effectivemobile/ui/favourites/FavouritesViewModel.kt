package com.example.effectivemobile.ui.favourites

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import com.example.effectivemobile.data.model.Offer
import com.example.effectivemobile.data.model.Vacancy
import com.example.effectivemobile.repository.MainRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class FavouritesViewModel @Inject constructor(private val repository: MainRepository) :
    ViewModel() {

    private val _vacancies = MutableLiveData<List<Vacancy>>()
    val vacancycies: LiveData<List<Vacancy>> get() = _vacancies

    fun getVacancy() {
        viewModelScope.launch {
            if (_vacancies.value == null) {
                // Загружаем данные только если они еще не были загружены
                val result = repository.getFavorites().asFlow().collect {
                    _vacancies.value = it
                }

            }
        }
    }

    fun onItemClicked(itemId: String) {
        viewModelScope.launch {
            val vacancy = repository.getVacancyById(itemId)
            vacancy?.let {
                if (it.isFavorite!!) {
                    repository.toggleFavorite(itemId)
                } else {
                    repository.deleteVacancy(itemId)
                }
            }
        }
    }
}