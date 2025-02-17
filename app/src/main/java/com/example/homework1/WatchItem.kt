package com.example.homework1

data class WatchItem(
    // watch item (movie/serial) composed by id, title and if it's already watched
    val id: Int,
    val title: String,
    val watched: Boolean = false
)
