package com.example.homework

sealed class State {
    object Loading : State()
    object Success : State()
    object ButtonActive : State()
    object ButtonNotActive: State()

}