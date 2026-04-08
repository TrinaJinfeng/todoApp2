package com.example.todoapp2

import com.example.todoapp2.repository.TodoRepository
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.Assert.*

class ExampleUnitTest {

    @Test
    fun testGetTodos() = runBlocking {
        val repository = TodoRepository()

        val result = repository.getTodos()

        assertNotNull(result)
        assertTrue(result.isNotEmpty())
    }
}