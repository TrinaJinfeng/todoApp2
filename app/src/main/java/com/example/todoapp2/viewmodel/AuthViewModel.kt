package com.example.todoapp2.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

// Define authentication state， more safe than using a String.
sealed class LoginState {
    object Loading : LoginState()
    object Success : LoginState()
    data class Error(val message: String) : LoginState()
}

class AuthViewModel : ViewModel() {

    // UI observe the state.
    val loginState = MutableLiveData<LoginState>()

    fun login(email: String, password: String) {

        viewModelScope.launch {

            // 1. Set the UI to a loading state first
            loginState.value = LoginState.Loading

            try {
                // 2. Simulate API request.
                delay(1000)

                // 3. Processing Result
                if (email.isNotEmpty() && password.isNotEmpty()) {
                    loginState.value = LoginState.Success
                } else {
                    loginState.value = LoginState.Error("Invalid input")
                }

            } catch (e: Exception) {
                // 4. Exception handling.
                loginState.value = LoginState.Error("Something went wrong")
            }
        }
    }
}