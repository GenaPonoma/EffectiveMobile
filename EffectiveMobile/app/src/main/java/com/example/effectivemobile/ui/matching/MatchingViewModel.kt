package com.example.effectivemobile.ui.matching

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.effectivemobile.data.model.Vacancy
import com.example.effectivemobile.repository.MainRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MatchingViewModel @Inject constructor(private val mainRepository: MainRepository) : ViewModel() {

    suspend fun getVacancy(): List<Vacancy> {
        return withContext(Dispatchers.IO) {
            mainRepository.getData().vacancies
        }
    }
    fun onItemClicked(itemId: String) {
        viewModelScope.launch {
            val vacancy = mainRepository.getVacancyById(itemId)
            vacancy?.let {
                if (it.isFavorite!!) {
                    mainRepository.toggleFavorite(itemId)
                } else {
                    mainRepository.deleteVacancy(itemId)
                }
            }
        }
    }
    fun saveItemToDatabase(itemId: String) {
        viewModelScope.launch {
            mainRepository.toggleFavorite(itemId)
        }
    }

}