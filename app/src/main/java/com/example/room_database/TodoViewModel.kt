package com.example.room_database

import android.icu.text.CaseMap.Title
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.room_database.dao.TodoDAO
import com.example.room_database.data.Todo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.net.IDN
import java.time.Instant
import java.util.Date
import java.util.stream.Stream
import javax.inject.Inject

@HiltViewModel
class TodoViewModel @Inject constructor (
    private val todoDAO:TodoDAO
) : ViewModel() {

    //val todoDAO = App.todoDatabase.getTodoDao()


    //private var _todoList = MutableLiveData<List<Todo>>()
    //val todoList:LiveData<List<Todo>> = _todoList
    val todoList:LiveData<List<Todo>> = todoDAO.getAllTodo()

    fun addTodo(title: String){
        //TodoManager.addTodo(title)
        //getAllTodo()

        viewModelScope.launch(Dispatchers.IO) {
            todoDAO.addTodo(Todo(title=title, createdAt = Date.from(Instant.now())))
        }

    }

    fun deleteTodo(id:Int){
        //TodoManager.deleteTodo(id)
        //getAllTodo()

        viewModelScope.launch(Dispatchers.IO) {
            todoDAO.deleteTodo(id)
        }
    }
}