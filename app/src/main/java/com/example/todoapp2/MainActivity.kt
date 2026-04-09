//package com.example.todoapp2
//
//import android.os.Bundle
//import android.widget.*
//import androidx.appcompat.app.AppCompatActivity
//import com.example.todoapp2.viewmodel.AuthViewModel
//import androidx.lifecycle.ViewModelProvider
//import android.content.Intent
//
//
//// MainActivity represents the login screen of the app
//class MainActivity : AppCompatActivity() {
//
//    // ViewModel used to handle authentication logic (MVVM architecture)
//    private lateinit var viewModel: AuthViewModel
//
//    //Called when the activity is first created
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//        //set the UI layout for the activity
//        setContentView(R.layout.activity_main)
//
//        // Initialize ViewModel (lifecycle-aware) --instance
//        viewModel = ViewModelProvider(this)[AuthViewModel::class.java]
//
//        // Get references to UI elements from layout
//        val emailInput = findViewById<EditText>(R.id.emailInput)
//        val passwordInput = findViewById<EditText>(R.id.passwordInput)
//        val loginBtn = findViewById<Button>(R.id.loginBtn)
//
//        // set click listener for login button
//        loginBtn.setOnClickListener {
//
//            //Retrieve user input from EditText fields
//            val email = emailInput.text.toString()
//            val password = passwordInput.text.toString()
//
//            //call login function in ViewModel
//            val success = viewModel.login(email, password)
//
//            // Handle login result
//            if (success) {
//                // Navigate to TodoActivity if login is successful
//                val intent = Intent(this, com.example.todoapp2.ui.TodoActivity::class.java)
//                startActivity(intent)
//            } else {
//                // Show error message if login fails
//                Toast.makeText(this, "Invalid input", Toast.LENGTH_SHORT).show()
//            }
//        }
//    }
//}

package com.example.todoapp2

import android.content.Intent
import android.os.Bundle
import android.service.voice.VisibleActivityInfo
import android.widget.*
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.todoapp2.viewmodel.AuthViewModel
import com.example.todoapp2.viewmodel.LoginState

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: AuthViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // initial viewModel
        viewModel = ViewModelProvider(this)[AuthViewModel::class.java]

        val emailInput = findViewById<EditText>(R.id.emailInput)
        val passwordInput = findViewById<EditText>(R.id.passwordInput)
        val loginBtn = findViewById<Button>(R.id.loginBtn)
        val loading = findViewById<ProgressBar>(R.id.loading)

        // 点击登录
        loginBtn.setOnClickListener {
            val email = emailInput.text.toString()
            val password = passwordInput.text.toString()

            viewModel.login(email, password) // ❗不再返回结果
        }

        // 👇 观察状态（核心！！）
        viewModel.loginState.observe(this) { state ->
            when (state) {

                is LoginState.Loading -> {
                    loading.visibility = View.VISIBLE
                    loginBtn.isEnabled = false // not submit twice
                }

                is LoginState.Success -> {
                    loading.visibility = View.GONE
                    loginBtn.isEnabled = true
                    startActivity(Intent(this, com.example.todoapp2.ui.TodoActivity::class.java))
                }

                is LoginState.Error -> {
                    loading.visibility = View.GONE
                    loginBtn.isEnabled = true
                    Toast.makeText(this, state.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}