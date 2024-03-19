package com.example.ixgcore

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.example.ixgcore.api.Constants
import com.example.ixgcore.datastore.DataStore
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Test

private val testContext: Context = ApplicationProvider.getApplicationContext()
private val dataStore = DataStore(testContext)

class DataStoreTest {

    @Test
    fun defaultServerURLTest() {
        runBlocking {
            val result = dataStore.getServerUrl()
            assert(result == Constants().baseURL)
        }
    }

    @Test
    fun serverURLTest() {
        val serverURL = "https://test.ixg.aiphone-app.net"

        runBlocking {
            dataStore.setServerUrl(serverURL)
            assert(dataStore.getServerUrl() == serverURL)
        }
    }

    @Test
    fun nameTest() {
        val name = "IXG"

        runBlocking {
            dataStore.setName(name)
            assert(dataStore.getName() == name)
        }
    }

    @Test
    fun propertyIdTest() {
        val propertyId = "1234"

        runBlocking {
            dataStore.setPropertyId(propertyId)
            assert(dataStore.getPropertyId() == propertyId)
        }
    }

    @Test
    fun qrCodeTest() {
        runBlocking {
            dataStore.setQRCode("1234")
            assert(dataStore.getQRCode() == "1234")
        }
    }

    @Test
    fun appSlotIdTest() {
        runBlocking {
            dataStore.setAppSlotId("4")
            assert(dataStore.getAppSlotId() == "4")
        }
    }

    @Test
    fun ixgAppInfoTest() {
        runBlocking {
            dataStore.setName("IXG")
            dataStore.setPropertyId("123")
            dataStore.setQRCode("123")
            dataStore.setAppSlotId("123")
            val ixgAppInfo = dataStore.getIXGAppInfo()
            assert(ixgAppInfo.name == "IXG")
            assert(ixgAppInfo.propertyId == "123")
            assert(ixgAppInfo.qrCode == "123")
            assert(ixgAppInfo.appSlotID == "123")
        }
    }

    @Test
    fun ixgAppInfoSetTest() {
        runBlocking {
            val ixgAppInfo = IXGAppInfo("IXG", "123", "123", "123")
            dataStore.setIXGAppInfo(ixgAppInfo)
            assert(dataStore.getName() == "IXG")
            assert(dataStore.getPropertyId() == "123")
            assert(dataStore.getQRCode() == "123")
            assert(dataStore.getAppSlotId() == "123")
        }
    }

    @Test
    fun secretKeyTest() {
        runBlocking {
            dataStore.setSecretKey("123")
            assert(dataStore.getSecretKey() == "123")
        }
    }

    @Test
    fun certTest() {
        runBlocking {
            dataStore.setCert("123")
            assert(dataStore.getCert() == "123")
        }
    }

    @After
    fun cleanUp() {
        runBlocking { dataStore.cleanUp() }
    }
}