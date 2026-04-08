package com.example.todoapp2.repository

import kotlinx.coroutines.delay

class TodoRepository {

    suspend fun getTodos(): List<String> {

        delay(1000)
        return listOf(
            "Learn MVVM",
            "Build Android App",
            "Prepare Interview"
        )
    }
}