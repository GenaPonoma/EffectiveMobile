package com.example.homework

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homework.repository.MainRepository
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch


class MainViewModel(private val repository: MainRepository) : ViewModel() {


    private val _state = MutableStateFlow<State>(State.ButtonNotActive)
    val state = _state.asStateFlow()
    private var _message = Channel<String>()
    val message = _message.receiveAsFlow()

    fun isActiveButton(text: String) {
        viewModelScope.launch {
            _state.value = State.Loading
            _message.send("По запросу \"" + repository.dataSearch(text) + "\" ничего не найдено")
            _state.value = State.Success
        }
    }

    fun validateField(toString: String) {
        viewModelScope.launch {
            val countStr = toString.length
            if (countStr <= 3) {
                _state.value = State.ButtonNotActive
            } else {
                _state.value = State.ButtonActive
            }
        }
    }
}


