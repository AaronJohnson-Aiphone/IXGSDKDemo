package com.example.ixgcore.api

interface IStationManager {

    suspend fun getStationList(): Result<List<Station>?>
}