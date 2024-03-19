package com.example.ixgsdkdemo.ui.screens.stationsList

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

data class IXGStation(val name: String, val description: String)
class StationsListViewModel: ViewModel() {
    private val _stationsList = mutableStateListOf<IXGStation>()
    var errorMessage: String by mutableStateOf("")
    val stationsList: List<IXGStation>
        get() = _stationsList

    init {
        viewModelScope.launch {
            getStationsList()
        }
    }

    private fun resetModel() {
        _stationsList.clear()
    }

    fun getStationsList() {
        // TODO: replace with remotely fetched data
        val data = listOf(
            IXGStation("Station 1","Audio"),
            IXGStation("Station 2", "Video"),
            IXGStation("Station 3", "Audio/Video")
        )
        _stationsList.clear()
        _stationsList.addAll(data)
    }
}