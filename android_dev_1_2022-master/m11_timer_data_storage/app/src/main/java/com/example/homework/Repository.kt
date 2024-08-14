package com.example.homework

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences

private const val PREFERENCE_NAME = "pref_name"
private const val PREFERENCE_KEY = "pref_key"
private var string_text_local: String? = null
private lateinit var sharedPreferences: SharedPreferences

class Repository(context: Context) {
    init {
        sharedPreferences = context.getSharedPreferences(PREFERENCE_NAME, MODE_PRIVATE)

    }

    //будет доставать значение из SharedPreference;
    private fun getDataFromSharedPreference(): String? {
        return sharedPreferences.getString(PREFERENCE_KEY, null)
    }

    //будет доставать значение, возвращать значение локальной переменной;
    private fun getDataFromLocalVariable(): String? {
        return string_text_local
    }

    //будет записывать значения и в SharedPreference, и в локальную переменную.
    fun saveText(text: String) {
        string_text_local = text
        sharedPreferences.edit().putString(PREFERENCE_KEY, string_text_local).apply()

    }

    //будет очищать значение и в SharedPreference, и в локальной переменной.
    fun clearText() {
        string_text_local = null
        sharedPreferences.edit().remove(PREFERENCE_KEY).apply()
    }

    //будет доставать значение из источников: сначала попытается взять значение локальной переменной; если оно null, то
    // попытаемся взять значение из SharedPreference.
    fun getText(): String {
        return when {
            string_text_local != null -> getDataFromLocalVariable()!!
            else -> getDataFromSharedPreference() ?: ""
        }

    }
}