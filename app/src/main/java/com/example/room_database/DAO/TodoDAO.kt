package com.example.room_database.DAO

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.room_database.data.Todo

@Dao
interface TodoDAO {
    @Query("SELECT * FROM Todo ORDER BY createdAt DESC")
    fun getAllTodo() : LiveData<List<Todo>>

    @Insert
    fun addTodo(todo : Todo)

    @Query("Delete FROM Todo where id = :id")
    fun deleteTodo(id : Int)
}