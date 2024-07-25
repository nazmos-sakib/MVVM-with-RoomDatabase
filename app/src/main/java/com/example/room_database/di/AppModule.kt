package com.example.room_database.di

import android.content.Context
import androidx.room.Room
import com.example.room_database.dao.TodoDAO
import com.example.room_database.room_DB.TodoDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule{

    @Provides
    fun provideTodoDAO(roomDB:TodoDatabase):TodoDAO{
        return roomDB.getTodoDao()
    }

    @Provides
    fun provideDatabase(@ApplicationContext ctx:Context):TodoDatabase{
        return Room.databaseBuilder(
            ctx,
            TodoDatabase::class.java,
            TodoDatabase.NAME,
        ).build()
    }
}