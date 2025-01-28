package com.example.effectivemobile.repository

import androidx.lifecycle.LiveData
import com.example.effectivemobile.data.model.Vacancy
import com.example.effectivemobile.data.model.Work
import com.example.effectivemobile.data.model.room.VacancyDao
import com.example.effectivemobile.retrofit.retrofit
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MainRepository() {
    suspend fun getData(): Work {
        return retrofit.getWorkApi(
            "1z4TbeDkbfXkvgpoJprXbN85uCcD7f00r",
            "download"
        )
    }

//    suspend fun toggleFavorite(vacancyId: String) {
//        val vacancy = dao.getVacancyById(vacancyId)
//        if (vacancy != null) {
//            val updatedVacancy = vacancy.copy(isFavorite = !vacancy.isFavorite!!)
//            dao.update(updatedVacancy)
//        }
//    }
//
////    fun getFavorites(): LiveData<List<Vacancy>> {
////        return dao.getFavorites()
////    }
//
//    private suspend fun getVacancyById(vacancyId: String): Vacancy? {
//        return withContext(Dispatchers.IO) {
//            dao.getVacancyById(vacancyId)
//        }
//    }

}
