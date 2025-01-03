package com.example.iconiconbuttonfloatingactionbutton

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val input = rememberSaveable { mutableStateOf("") }
            val notesList = rememberSaveable { mutableListOf<String>() }
            // Преобразуем его в SnapshotStateList для использования в Compose
            val notesStateList = remember { mutableStateListOf<String>() }

            // Обновляем notesStateList при изменении notesList
            notesStateList.clear()
            notesStateList.addAll(notesList)

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp)
                    .background(color = Color.LightGray, shape = RoundedCornerShape(8.dp))
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    Text(
                        text = "Заметки",
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                color = Color.DarkGray,
                                shape = RoundedCornerShape(8.dp)
                            )
                            .padding(12.dp),
                        fontWeight = FontWeight.Bold,
                        fontSize = 26.sp,
                        color = Color.White,
                        textAlign = TextAlign.Center
                    )
                    OutlinedTextField(
                        value = input.value,
                        onValueChange = { input.value = it },
                        textStyle = TextStyle(fontSize = 22.sp, textAlign = TextAlign.Center),
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(color = Color.White, shape = RoundedCornerShape(8.dp)),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color.Black,
                            unfocusedBorderColor = Color.Black
                        ),
                        placeholder = {
                            Text(
                                text = "Введите текст...",
                                modifier = Modifier
                                    .fillMaxWidth(),
                                fontSize = 22.sp,
                                textAlign = TextAlign.Center
                            )
                        }
                    )
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                            .padding(4.dp)
                    ) {
                        items(notesStateList) { item ->
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(4.dp)
                                    .background(
                                        color = Color.White,
                                        shape = RoundedCornerShape(20.dp)
                                    )
                                    .padding(
                                        top = 4.dp,
                                        bottom = 4.dp,
                                        start = 15.dp,
                                        end = 15.dp
                                    ),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = item,
                                    fontSize = 20.sp
                                )
                                IconButton(
                                    onClick = {
                                        notesList.remove(item)
                                        notesStateList.clear()
                                        notesStateList.addAll(notesList)
                                    }
                                ) {
                                    Icon(Icons.Default.Delete, "", tint = Color.DarkGray)
                                }
                            }
                        }
                    }
                }
                FloatingActionButton(
                    modifier = Modifier
                        .align(alignment = Alignment.BottomEnd)
                        .padding(8.dp),
                    onClick = {
                        if (input.value.isNotBlank()) {
                            notesList.add(input.value)
                            input.value = ""
                        }
                    },
                    contentColor = Color.White,
                    containerColor = Color.DarkGray
                ) {
                    Icon(Icons.Filled.Add, "")
                }
            }
        }
    }
}