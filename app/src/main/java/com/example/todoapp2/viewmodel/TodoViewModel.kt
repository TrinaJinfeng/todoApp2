package com.example.todoapp2.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoapp2.repository.TodoRepository
import kotlinx.coroutines.launch

class TodoViewModel : ViewModel() {

    private val repository = TodoRepository() //manual DI

    fun getTodos(callback: (List<String>) -> Unit) {
        viewModelScope.launch {
            val todos = repository.getTodos()
            callback(todos)
        }
    }
}