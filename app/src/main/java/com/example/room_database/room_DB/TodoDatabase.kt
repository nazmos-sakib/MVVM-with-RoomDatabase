package com.example.room_database.room_DB

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.room_database.DAO.TodoDAO
import com.example.room_database.data.Todo

@Database(entities = [Todo::class], version = 1)
@TypeConverters(Converters::class)
abstract class TodoDatabase: RoomDatabase() {
    companion object{
        const val NAME = "Todo_DB"
    }

    abstract fun getTodoDao():TodoDAO
}