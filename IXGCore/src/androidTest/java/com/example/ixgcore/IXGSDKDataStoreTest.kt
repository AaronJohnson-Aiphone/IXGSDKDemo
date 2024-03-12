package com.example.ixgcore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStoreFile
import androidx.test.core.app.ApplicationProvider
import com.example.ixgcore.datastore.IXGSDKDataStore
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Test

private const val TEST_DATASTORE_NAME: String = "test_datastore"
private val testContext: Context = ApplicationProvider.getApplicationContext()
private val testDataStore: DataStore<Preferences> =
    PreferenceDataStoreFactory.create(produceFile = {
        testContext.preferencesDataStoreFile(TEST_DATASTORE_NAME)
    })
private val ixgsdkDataStore = IXGSDKDataStore(testDataStore)

class IXGSDKDataStoreTest {
    @Test
    fun defaultServerURLTest() {
        runBlocking {
            val result = ixgsdkDataStore.getServerUrl()
            assert(result == "https://api-ixg1-r2.ixg.aiphone-app.net")
        }
    }

    @Test
    fun serverURLTest() {
        val serverURL = "https://test.ixg.aiphone-app.net"

        runBlocking {
            ixgsdkDataStore.setServerUrl(serverURL)
            assert(ixgsdkDataStore.getServerUrl() == serverURL)
        }
    }

    @Test
    fun nameTest() {
        val name = "IXG"

        runBlocking {
            ixgsdkDataStore.setName(name)
            assert(ixgsdkDataStore.getName() == name)
        }
    }

    @Test
    fun propertyIdTest() {
        val propertyId = "1234"

        runBlocking {
            ixgsdkDataStore.setPropertyId(propertyId)
            assert(ixgsdkDataStore.getPropertyId() == propertyId.toInt())
        }
    }

    @Test
    fun qrCodeTest() {
        runBlocking {
            ixgsdkDataStore.setQRCode("1234")
            assert(ixgsdkDataStore.getQRCode() == "1234")
        }
    }

    @Test
    fun appSlotIdTest() {
        runBlocking {
            ixgsdkDataStore.setAppSlotId("4")
            assert(ixgsdkDataStore.getAppSlotId() == 4)
        }
    }

    @Test
    fun ixgAppInfoTest() {
        runBlocking {
            ixgsdkDataStore.setName("IXG")
            ixgsdkDataStore.setPropertyId("123")
            ixgsdkDataStore.setQRCode("123")
            ixgsdkDataStore.setAppSlotId("123")
            val ixgAppInfo = ixgsdkDataStore.getIXGAppInfo()
            assert(ixgAppInfo.name == "IXG")
            assert(ixgAppInfo.propertyId == 123)
            assert(ixgAppInfo.qrCode == "123")
            assert(ixgAppInfo.appSlotID == 123)
        }
    }

    @Test
    fun ixgAppInfoSetTest() {
        runBlocking {
            val ixgAppInfo = IXGAppInfo("IXG", 123, "123", 123)
            ixgsdkDataStore.setIXGAppInfo(ixgAppInfo)
            assert(ixgsdkDataStore.getName() == "IXG")
            assert(ixgsdkDataStore.getPropertyId() == 123)
            assert(ixgsdkDataStore.getQRCode() == "123")
            assert(ixgsdkDataStore.getAppSlotId() == 123)
        }
    }

    @After
    fun cleanUp() {
        runBlocking {
            testDataStore.edit { preferences ->
                preferences.clear()
            }
        }
    }
}