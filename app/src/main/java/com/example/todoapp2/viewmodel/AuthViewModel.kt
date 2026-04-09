//package com.example.todoapp2.viewmodel
//
//import androidx.lifecycle.ViewModel
//
//class AuthViewModel : ViewModel(){
//    fun login(email:String, password:String): Boolean {
//        return email.isNotEmpty() && password.isNotEmpty()
//    }
//}

package com.example.todoapp2.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

// 定义登录状态（比String更安全、更专业）
sealed class LoginState {
    object Loading : LoginState()
    object Success : LoginState()
    data class Error(val message: String) : LoginState()
}

class AuthViewModel : ViewModel() {

    // UI观察这个状态
    val loginState = MutableLiveData<LoginState>()

    fun login(email: String, password: String) {

        viewModelScope.launch {

            // 1️⃣ 先通知UI进入loading状态
            loginState.value = LoginState.Loading

            try {
                // 2️⃣ 模拟API请求
                delay(1000)

                // 3️⃣ 处理结果
                if (email.isNotEmpty() && password.isNotEmpty()) {
                    loginState.value = LoginState.Success
                } else {
                    loginState.value = LoginState.Error("Invalid input")
                }

            } catch (e: Exception) {
                // 4️⃣ 异常处理（真实项目必须有）
                loginState.value = LoginState.Error("Something went wrong")
            }
        }
    }
}