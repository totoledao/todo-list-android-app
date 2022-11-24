package com.totoledao.todolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity() {

    private lateinit var todoAdapter: TodoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        todoAdapter = TodoAdapter(mutableListOf())

        val rvTodoItems = findViewById<RecyclerView>(R.id.rvTodoItems)
        rvTodoItems.adapter = todoAdapter
        rvTodoItems.layoutManager = LinearLayoutManager(this)

        val btAddTodo = findViewById<Button>(R.id.btAddTodo)
        val btRemoveTodos = findViewById<Button>(R.id.btRemoveTodos)
        val inputTodo = findViewById<EditText>(R.id.inputTodo)

        btAddTodo.setOnClickListener {
            val todoTitle = inputTodo.text.toString()
            if(todoTitle.isNotEmpty()) {
                val todo = Todo(todoTitle)
                todoAdapter.addTodo(todo)
                inputTodo.text.clear()
            }
        }

        btRemoveTodos.setOnClickListener {
            todoAdapter.removeDoneTodos()
        }

    }
}