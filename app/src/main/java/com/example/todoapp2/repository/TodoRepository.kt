package com.example.todoapp2.repository
import com.example.todoapp2.model.Account
import kotlinx.coroutines.delay

class TodoRepository {

    suspend fun getAccounts(): List<Account> {

        delay(1000) //simulate API
        return listOf(
            Account("Savings", 5000.0),
            Account("Checking", 1200.0),
            Account("Credit Card", -300.0)
        )
    }
}