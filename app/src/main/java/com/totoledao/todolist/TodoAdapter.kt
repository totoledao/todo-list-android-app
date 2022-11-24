package com.totoledao.todolist

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TodoAdapter(
    private val todos: MutableList<Todo>
) : RecyclerView.Adapter<TodoAdapter.TodoViewHodler>() {

    class TodoViewHodler (
        itemView: View
    ) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHodler {
        return TodoViewHodler(
            LayoutInflater.from(
                parent.context
            ).inflate(
                R.layout.item_todo,
                parent,
                false
            )
        )
    }

    fun addTodo (todo: Todo) {
        todos.add(todo)
        notifyItemInserted(todos.size - 1)
    }

    fun removeDoneTodos () {
        todos.removeAll { todo ->
            todo.isChecked
        }
        notifyDataSetChanged()
    }

    private fun toggleStrikeThrough(tvTodoItem: TextView, isChecked: Boolean)  {
        if(isChecked){
            tvTodoItem.paintFlags = tvTodoItem.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        } else {
            tvTodoItem.paintFlags = tvTodoItem.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
        }
    }

    override fun onBindViewHolder(holder: TodoViewHodler, position: Int) {
        val curTodo = todos[position]
        val tvTodoItem = holder.itemView.findViewById<TextView>(R.id.tvTodoItem)
        val cbTodoItem = holder.itemView.findViewById<CheckBox>(R.id.cbTodoItem)

        tvTodoItem.text = curTodo.title
        cbTodoItem.isChecked = curTodo.isChecked
        toggleStrikeThrough(tvTodoItem, curTodo.isChecked)
        cbTodoItem.setOnCheckedChangeListener { _, isChecked ->
            toggleStrikeThrough(tvTodoItem, isChecked)
            curTodo.isChecked = !curTodo.isChecked
        }
    }

    override fun getItemCount(): Int {
        return todos.size
    }
}