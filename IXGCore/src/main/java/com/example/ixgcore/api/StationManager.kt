package com.example.ixgcore.api

import android.util.Log
import com.example.ixgcore.api.data.ACLAddressBookData
import com.example.ixgcore.api.data.AddressBookRequestData
import com.example.ixgcore.api.data.AddressBookRequestWrapper
import com.example.ixgcore.api.data.AddressBookResponseWrapper
import com.example.ixgcore.datastore.DataStore
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

class StationManager(
    private val dataStore: DataStore,
    private val apiService: IService,
    private val constants: Constants): IStationManager {
    override suspend fun getStationList(): Result<List<Station>?> {
        val addressBookRequestData = AddressBookRequestData(sid = constants.getSidFromDate(), sys = constants.sys, propertyID = dataStore.getPropertyId(),
            clientID = dataStore.getIXGAppInfo().appSlotID, registrationCode = dataStore.getRegistrationCode())

        val addressBookWrapper = AddressBookRequestWrapper(addressBookRequestData)
        val response = apiService.getAddressBook(addressBookWrapper)

        return if (response.isSuccessful) {
            val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
            val adapter: JsonAdapter<AddressBookResponseWrapper> = moshi.adapter(AddressBookResponseWrapper::class.java)
            val networkModel = adapter.fromJson(response.body()!!)
            Log.d("StationManager", "post model conversion: $networkModel")

            val addressBookData = networkModel!!.addressBookResponseData
            val stations = convertACLAddressBookToStationList(addressBookData.addressBookData)

            Result.success(stations)

        } else if (response.code() == 410) {
            Result.failure(Exception("TODO: handle 410"))
        } else {
            Result.failure(Exception("Unhandled status code ${response.code()}"))
        }
    }

    private fun convertACLAddressBookToStationList(addressBookData: ACLAddressBookData): List<Station> {
        val stations = mutableListOf<Station>()
        //TODO convert addressBookData to List<Station>
        for(i in 0 until addressBookData.stationNumbers.size){
            val station = Station(number = addressBookData.stationNumbers[i].toInt(),
                name = addressBookData.stationNames[i].substringAfter("&&&"),
                type = addressBookData.stationTypes[i], canUnlock = addressBookData.unlockAuthorization[i], soundCodec = addressBookData.soundCodec[i])
            stations.add(station)
        }


        return stations
    }
}