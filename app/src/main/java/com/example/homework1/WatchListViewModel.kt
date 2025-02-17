package com.example.homework1

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

class WatchListViewModel : ViewModel() {
    // watch list
    private val _watchList = mutableStateListOf<WatchItem>()
    val watchList: List<WatchItem> get() = _watchList

    // start the app with few entries pre-filled
    init {
        // Add some items
        _watchList.addAll(
            listOf(
                WatchItem(id = 1, title = "Breaking Bad"),
                WatchItem(id = 2, title = "Stranger Things"),
                WatchItem(id = 3, title = "The Witcher"),
                WatchItem(id = 4, title = "Game of Thrones"),
                WatchItem(id = 5, title = "The Mandalorian")
            )
        )
    }

    // add item to the list
    fun addItem(title: String){
        val newItem = WatchItem(
            id = _watchList.size+1,
            title = title
        )
        _watchList.add(newItem)
    }

    // Mark as watched
    fun markWatched(item: WatchItem){
        val index = _watchList.indexOf(item)
        _watchList[index] = item.copy(watched=!item.watched)
    }

    // Remove from the list
    fun removeItem(item: WatchItem){
        _watchList.remove(item)
    }
}