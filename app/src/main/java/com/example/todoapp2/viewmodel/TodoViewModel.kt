package com.example.todoapp2.viewmodel

import com.example.todoapp2.model.Account
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.MutableLiveData
import com.example.todoapp2.repository.TodoRepository
import kotlinx.coroutines.launch

sealed class AccountState {
    object Loading : AccountState()
    data class Success(val accounts: List<Account>) : AccountState()
    data class Error(val message: String) : AccountState()
}

class TodoViewModel : ViewModel() {

    private val repository = TodoRepository() //manual DI

    val accountState = MutableLiveData<AccountState>()

    fun getAccounts() {

        viewModelScope.launch {

            accountState.value = AccountState.Loading

            try {
                val accounts = repository.getAccounts()
                accountState.value = AccountState.Success(accounts)

            } catch (e: Exception) {
                accountState.value = AccountState.Error("Failed to load accounts")
            }
        }
    }
}