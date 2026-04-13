package com.example.todoapp2

import android.content.Intent
import android.os.Bundle
//import android.service.voice.VisibleActivityInfo
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

        // Click Login
        loginBtn.setOnClickListener {
            val email = emailInput.text.toString()
            val password = passwordInput.text.toString()

            viewModel.login(email, password) // no outcome returned.
        }

        // Observe the state.
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