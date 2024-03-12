package com.example.ixgcore.api

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.example.ixgcore.IRegistrationManager
import com.example.ixgcore.datastore.IIXGSDKDataStore
import com.example.ixgcore.datastore.IXGSDKDataStore
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.security.cert.X509Certificate
import javax.inject.Singleton
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

object
IXGCoreModule {
    
    private val Context.userDataStore: DataStore<Preferences> by preferencesDataStore(
        name = "com.example.ixg_core.user_preferences"
    )

    @Singleton
    fun provideUserDataStorePreferences(
        applicationContext: Context
    ): DataStore<Preferences> {
        return applicationContext.userDataStore
    }


    fun provideBaseUrl() = "https://api-ixg1-r2.ixg.aiphone-app.net"

    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient, BASE_URL:String): Retrofit = Retrofit.Builder()
        .addConverterFactory(ScalarsConverterFactory.create())
        .addConverterFactory(GsonConverterFactory.create(
            GsonBuilder()
                .setLenient()
                .create()))
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .build()


    @Singleton
    fun provideIXGSDKManager(registrationManager: IRegistrationManager): IRegistrationManager = registrationManager
    @Singleton
    fun provideApiService(retrofit: Retrofit): IIXGAPIService = retrofit.create(IIXGAPIService::class.java)

    @Singleton
    fun provideIXGSDKDataStore(ixgDataStore: IXGSDKDataStore): IIXGSDKDataStore = ixgDataStore
}