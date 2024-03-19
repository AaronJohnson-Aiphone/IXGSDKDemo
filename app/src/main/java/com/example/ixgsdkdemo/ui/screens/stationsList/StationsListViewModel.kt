package com.example.ixgsdkdemo.ui.screens.stationsList

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ixgcore.api.IStationsManager
import com.example.ixgcore.api.Station
import kotlinx.coroutines.launch

class StationsListViewModel(
    private val stationsManager: IStationsManager
): ViewModel() {

    private val _stationsList = mutableStateListOf<Station>()
    var errorMessage: String by mutableStateOf("")
    val stationsList: List<Station>
        get() = _stationsList

    init {
        viewModelScope.launch {
            getStationsList()
        }
    }

    private fun resetModel() {
        _stationsList.clear()
    }

    private suspend fun getStationsList() {
        val result = stationsManager.getStations()
        if (result.isSuccess) {
            result.getOrNull()?.let {
                _stationsList.clear()
                _stationsList.addAll(it)
            }
        } else {
            throw result.exceptionOrNull() ?: Exception("This is some error")
        }
    }
}