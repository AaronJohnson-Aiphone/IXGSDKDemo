package com.example.ixgcore.datastore

import com.example.ixgcore.IXGAppInfo

interface IDataStore {

    suspend fun setServerUrl(serverUrl: String)

    suspend fun getServerUrl(): String

    suspend fun setName(name: String)

    suspend fun getName(): String

    suspend fun setPropertyId(propertyId: String)

    suspend fun getPropertyId(): Int

    suspend fun setQRCode(qrCode: String)

    suspend fun getQRCode(): String

    suspend fun setAppSlotId(appSlotId: String)

    suspend fun getAppSlotId(): Int

    suspend fun setIXGAppInfo(appInfo: IXGAppInfo)

    suspend fun getIXGAppInfo(): IXGAppInfo

    suspend fun cleanUp()
}