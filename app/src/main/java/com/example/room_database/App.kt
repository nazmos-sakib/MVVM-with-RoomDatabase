package com.example.room_database

import android.app.Application
import androidx.room.Room
import com.example.room_database.room_DB.TodoDatabase

class App : Application() {

    companion object{
        lateinit var todoDatabase: TodoDatabase
    }

    override fun onCreate() {
        super.onCreate()

        todoDatabase = Room.databaseBuilder(
            applicationContext,
            TodoDatabase::class.java,
            TodoDatabase.NAME,
        ).build()
    }
}