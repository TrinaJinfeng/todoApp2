package com.example.todoapp2.viewmodel

import androidx.lifecycle.ViewModel

class AuthViewModel : ViewModel(){
    fun login(email:String, password:String): Boolean {
        return email.isNotEmpty() && password.isNotEmpty()
    }
}