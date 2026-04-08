package com.example.todoapp2.ui

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.example.todoapp2.R
import androidx.lifecycle.ViewModelProvider
import com.example.todoapp2.viewmodel.TodoViewModel

class TodoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_todo)

        val listView = findViewById<ListView>(R.id.todoListView)

        val viewModel = ViewModelProvider(this).get(TodoViewModel::class.java)

        viewModel.getTodos { todos ->

            val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, todos)
            listView.adapter = adapter
        }
    }
}