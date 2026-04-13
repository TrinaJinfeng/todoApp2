package com.example.todoapp2.ui

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.todoapp2.R
import androidx.lifecycle.ViewModelProvider
import com.example.todoapp2.viewmodel.AccountState
import com.example.todoapp2.viewmodel.TodoViewModel

class TodoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_todo)

        val listView = findViewById<ListView>(R.id.todoListView)
        val loading = findViewById<ProgressBar>(R.id.loading)

        val viewModel = ViewModelProvider(this).get(TodoViewModel::class.java)

        viewModel.getAccounts() // Trigger data loading

        //observe the state
        viewModel.accountState.observe(this){ state ->
            when (state) {
                is AccountState.Loading -> {
                    loading?.visibility = View.VISIBLE
                }

                is AccountState.Success -> {
                    loading?.visibility = View.GONE

                    val displayList = state.accounts.map {
                        "${it.name}: $${it.balance}"
                    }

                    val adapter = ArrayAdapter(
                        this,
                        android.R.layout.simple_list_item_1,
                        displayList
                    )
                    listView.adapter = adapter
                }
                is AccountState.Error -> {
                    loading?.visibility = View.GONE
                    Toast.makeText(this, state.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}