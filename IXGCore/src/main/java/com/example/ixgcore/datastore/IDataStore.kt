package com.example.ixgcore.datastore

import com.example.ixgcore.IXGAppInfo

interface IDataStore {

    suspend fun setServerUrl(serverUrl: String)

    suspend fun getServerUrl(): String

    suspend fun setName(name: String)

    suspend fun getName(): String

    suspend fun setPropertyId(propertyId: String)

    suspend fun getPropertyId(): String

    suspend fun setQRCode(qrCode: String)

    suspend fun getQRCode(): String

    suspend fun setAppSlotId(appSlotId: String)

    suspend fun getAppSlotId(): String

    suspend fun setIXGAppInfo(appInfo: IXGAppInfo)

    suspend fun getIXGAppInfo(): IXGAppInfo

    suspend fun setSecretKey(secretKey: String)

    suspend fun getSecretKey(): String

    suspend fun setCert(cert: String)

    suspend fun getCert(): String

    suspend fun cleanUp()
}