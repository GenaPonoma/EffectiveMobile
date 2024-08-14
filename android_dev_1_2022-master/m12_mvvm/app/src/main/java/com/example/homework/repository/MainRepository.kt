package com.example.homework.repository

import kotlinx.coroutines.delay

class MainRepository {
    suspend fun dataSearch(text: String): String {
        delay(3000)
        return text
    }
}