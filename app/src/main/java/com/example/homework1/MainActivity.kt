package com.example.homework1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.homework1.ui.theme.Homework1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Homework1Theme {
                WatchListScreen()
            }
        }
    }
}

@Composable
fun WatchListScreen(viewModel: WatchListViewModel = viewModel()) {
    val watchList = viewModel.watchList

    Column(
        modifier = Modifier
            .padding(16.dp)
    ) {
        // add nem items
        var newItemTitle by remember { mutableStateOf("") }
        TextField(
            value = newItemTitle,
            onValueChange = { newItemTitle = it },
            label = { Text("Add movie/serial") },
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = {
                if (newItemTitle.isNotBlank()) {
                    viewModel.addItem(newItemTitle)
                    newItemTitle = ""
                }
            },
            modifier = Modifier.padding(top = 8.dp)
        ) {
            Text("Add")
        }

        // List of items
        LazyColumn(
            modifier = Modifier.padding(top = 16.dp)
        ) {
            items(watchList) { item ->
                WatchListItem(
                    item = item,
                    onWatchedClick = { viewModel.markWatched(item) },
                    onDeleteClick = { viewModel.removeItem(item) }
                )
            }
        }
    }
}

@Composable
fun WatchListItem(
    item: WatchItem,
    onWatchedClick: () -> Unit,
    onDeleteClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = item.title,
            style = if (item.watched) {
                MaterialTheme.typography.bodyMedium.copy(textDecoration = TextDecoration.LineThrough)
            } else {
                MaterialTheme.typography.bodyMedium
            }
        )
        Row {
            Checkbox(
                checked = item.watched,
                onCheckedChange = { onWatchedClick() }
            )
            IconButton(onClick = onDeleteClick) {
                Icon(Icons.Default.Delete, contentDescription = "Remover")
            }
        }
    }
}