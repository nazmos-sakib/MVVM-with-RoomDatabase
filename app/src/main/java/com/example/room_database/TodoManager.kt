package com.example.room_database

import android.provider.ContactsContract.Data
import com.example.room_database.data.Todo
import java.time.Instant
import java.util.Date

object TodoManager {
    private val todoList = mutableListOf<Todo>()
    fun getAllTodo():List<Todo>{
        return todoList
    }

    fun addTodo(title:String){
        todoList.add(Todo(System.currentTimeMillis().toInt(),title,Date.from(Instant.now())))
    }

    fun deleteTodo(id:Int){
        todoList.removeIf{
            it.id == id
        }
    }
}