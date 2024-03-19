package com.example.ixgcore.api

interface IStationsManager {

    suspend fun getStations(): Result<List<Station>?>
}