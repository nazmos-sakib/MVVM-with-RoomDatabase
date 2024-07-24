package com.example.room_database

import android.content.ClipData.Item
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.room_database.data.Todo
import com.example.room_database.data.getFakeTodo
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
fun TodoListPage(viewModel:TodoViewModel) {
    val todoList  by   viewModel.todoList.observeAsState()
    var inputText by remember {
        mutableStateOf("")
    }
 
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .padding(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .height(IntrinsicSize.Max),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                modifier= Modifier
                    .weight(1f),
                shape = RoundedCornerShape(7.dp, 0.dp, 0.dp, 7.dp),
                value = inputText,
                onValueChange = {
                    inputText = it
                })


                Button(
                    modifier = Modifier
                        .fillMaxHeight() ,
                    shape = RoundedCornerShape(0.dp, 7.dp, 7.dp, 0.dp),
                    onClick = {
                        viewModel.addTodo(inputText)
                        inputText = ""
                    }
                ) {
                    Text(text = "Add")
                }


        }

        //safe call
        todoList?.let {
            LazyColumn {
                itemsIndexed(it){ index, item ->
                    TodoItem(
                        item = item
                    ) { id ->
                        viewModel.deleteTodo(id)
                    }
                }
            }
        }?: Text(
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            text = "No items yet",
            fontSize = 16.sp
        )

    }
}

@Composable
fun TodoItem(item:Todo,onDelete:(Int)->Unit){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .border(
                border = BorderStroke(
                    width = 1.dp,
                    color = Color.White
                ),
                shape = RoundedCornerShape(7.dp)
            )
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = SimpleDateFormat("HH:mm:aa, dd/mm", Locale.GERMANY).format(item.createdAt),
                fontSize = 10.sp,
                color = Color.LightGray
            )
            Text(text = item.title,fontSize = 20.sp,
                color = Color.White)
        }

        IconButton(onClick = {
            onDelete(item.id)
        }) {
            Icon(painter = painterResource(id = R.drawable.baseline_delete_outline_24), contentDescription = "Delete", tint = Color.White)
        }
    }
}