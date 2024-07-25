package com.example.room_database

import android.app.Application
import androidx.room.Room
import com.example.room_database.room_DB.TodoDatabase
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {
    override fun onCreate() {
        super.onCreate()

    }
}