package com.example.effectivemobile.ui.matching

import androidx.lifecycle.ViewModel
import com.example.effectivemobile.data.model.Vacancy
import com.example.effectivemobile.repository.MainRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MatchingViewModel(private val mainRepository: MainRepository) : ViewModel() {

    suspend fun getVacancy(): List<Vacancy> {
        return withContext(Dispatchers.IO) {
            mainRepository.getData().vacancies
        }
    }


}