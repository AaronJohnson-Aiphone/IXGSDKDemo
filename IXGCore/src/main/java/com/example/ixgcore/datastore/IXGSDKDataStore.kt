package com.example.ixgcore.datastore

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import com.example.ixgcore.IXGAppInfo
import com.example.ixgcore.api.IXGAPIConstants
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class IXGSDKDataStore @Inject constructor(
    private val userDataStorePreferences: DataStore<Preferences>
) : IIXGSDKDataStore {

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
        setData(serverUrl, SDKPreferenceKeyDef.SERVER_URL)
    }

    override suspend fun getServerUrl(): String = getData(SDKPreferenceKeyDef.SERVER_URL).firstOrNull() ?: IXGAPIConstants().defaultServerURl

    override suspend fun setName(name: String) {
        setData(name, SDKPreferenceKeyDef.NAME)
    }

    override suspend fun getName(): String = getData(SDKPreferenceKeyDef.NAME).firstOrNull() ?: ""

    override suspend fun setPropertyId(propertyId: String) {
        setData(propertyId, SDKPreferenceKeyDef.PROPERTY_ID)
    }

    override suspend fun getPropertyId(): Int = getData(SDKPreferenceKeyDef.PROPERTY_ID).firstOrNull()?.toInt() ?: -1

    override suspend fun setQRCode(qrCode: String) {
        setData(qrCode, SDKPreferenceKeyDef.QR_CODE)
    }

    override suspend fun getQRCode(): String = getData(SDKPreferenceKeyDef.QR_CODE).firstOrNull() ?: ""

    override suspend fun setAppSlotId(appSlotId: String) {
        setData(appSlotId, SDKPreferenceKeyDef.APP_SLOT_ID)
    }

    override suspend fun getAppSlotId(): Int = getData(SDKPreferenceKeyDef.APP_SLOT_ID).firstOrNull()?.toInt() ?: -1


    override suspend fun getIXGAppInfo(): IXGAppInfo {
        return IXGAppInfo(getName(), getPropertyId(), getQRCode(), getAppSlotId())
    }

    override suspend fun setIXGAppInfo(appInfo: IXGAppInfo) {
        setName(appInfo.name)
        setPropertyId(appInfo.propertyId.toString())
        setQRCode(appInfo.qrCode)
        setAppSlotId(appInfo.appSlotID.toString())
    }
}