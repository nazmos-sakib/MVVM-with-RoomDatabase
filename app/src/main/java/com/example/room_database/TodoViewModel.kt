package com.example.room_database

import android.icu.text.CaseMap.Title
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.room_database.data.Todo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.net.IDN
import java.time.Instant
import java.util.Date
import java.util.stream.Stream

class TodoViewModel : ViewModel() {

    val todoDAO = App.todoDatabase.getTodoDao()


    //private var _todoList = MutableLiveData<List<Todo>>()
    //val todoList:LiveData<List<Todo>> = _todoList
    val todoList:LiveData<List<Todo>> = todoDAO.getAllTodo()

    init {
        getAllTodo()
    }

    private fun getAllTodo(){
        //_todoList.value = TodoManager.getAllTodo().reversed()

    }

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