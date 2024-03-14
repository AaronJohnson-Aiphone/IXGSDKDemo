package com.example.ixgcore.datastore

import android.content.Context
import android.util.Log
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStoreFile
import com.example.ixgcore.IXGAppInfo
import com.example.ixgcore.api.Constants
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking
import java.io.IOException

private const val TEST_DATASTORE_NAME: String = "test_datastore"

class DataStore(context: Context) : IDataStore {
    private val constants = Constants()

    private val userDataStorePreferences = PreferenceDataStoreFactory.create(produceFile = {
        context.preferencesDataStoreFile(TEST_DATASTORE_NAME)
        })

    private suspend fun setData(newData: String, key: Preferences.Key<String>){
        userDataStorePreferences.edit { preferences ->
            preferences[key] = newData
        }
    }

    private suspend fun getData(key: Preferences.Key<String>): Flow<String?> = userDataStorePreferences.data
        .catch {  exception ->
            // dataStore.data throws an IOException when an error is encountered when reading data
            if (exception is IOException) {
                Log.e("Error reading preferences.", exception.toString())
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }.map { preferences ->
            preferences[key]
        }

    override suspend fun setServerUrl(serverUrl: String) {
        setData(serverUrl, PreferenceKeyDef.SERVER_URL)
    }

    override suspend fun getServerUrl(): String = getData(PreferenceKeyDef.SERVER_URL).firstOrNull() ?: constants.baseURL

    override suspend fun setName(name: String) {
        setData(name, PreferenceKeyDef.NAME)
    }

    override suspend fun getName(): String = getData(PreferenceKeyDef.NAME).firstOrNull() ?: ""

    override suspend fun setPropertyId(propertyId: String) {
        setData(propertyId, PreferenceKeyDef.PROPERTY_ID)
    }

    override suspend fun getPropertyId(): Int = getData(PreferenceKeyDef.PROPERTY_ID).firstOrNull()?.toInt() ?: -1

    override suspend fun setQRCode(qrCode: String) {
        setData(qrCode, PreferenceKeyDef.QR_CODE)
    }

    override suspend fun getQRCode(): String = getData(PreferenceKeyDef.QR_CODE).firstOrNull() ?: ""

    override suspend fun setAppSlotId(appSlotId: String) {
        setData(appSlotId, PreferenceKeyDef.APP_SLOT_ID)
    }

    override suspend fun getAppSlotId(): Int = getData(PreferenceKeyDef.APP_SLOT_ID).firstOrNull()?.toInt() ?: -1


    override suspend fun getIXGAppInfo(): IXGAppInfo {
        return IXGAppInfo(getName(), getPropertyId(), getQRCode(), getAppSlotId())
    }

    override suspend fun setIXGAppInfo(appInfo: IXGAppInfo) {
        setName(appInfo.name)
        setPropertyId(appInfo.propertyId.toString())
        setQRCode(appInfo.qrCode)
        setAppSlotId(appInfo.appSlotID.toString())
    }

    override suspend fun cleanUp() {
        runBlocking {
            userDataStorePreferences.edit { preferences ->
                preferences.clear()
            }
        }
    }
}