package com.example.ixgcore.api

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.example.ixgcore.IRegistrationManager
import com.example.ixgcore.datastore.IIXGSDKDataStore
import com.example.ixgcore.datastore.IXGSDKDataStore
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.security.cert.X509Certificate
import javax.inject.Singleton
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager
@Module
@InstallIn(SingletonComponent::class)
object
IXGCoreModule {
    
    private val Context.userDataStore: DataStore<Preferences> by preferencesDataStore(
        name = "com.example.ixg_core.user_preferences"
    )

    @Provides
    @Singleton
    fun provideUserDataStorePreferences(
        @ApplicationContext applicationContext: Context
    ): DataStore<Preferences> {
        return applicationContext.userDataStore
    }

    @Provides
    fun provideBaseUrl() = "https://api-ixg1-r2.ixg.aiphone-app.net"

    @Provides
    @Singleton
    fun provideUnsafeOkHttpClient(): OkHttpClient {
        val x509TrustManager = object: X509TrustManager {
            override fun checkClientTrusted(chain: Array<out X509Certificate>?, authType: String?) { }
            override fun checkServerTrusted(chain: Array<out X509Certificate>?, authType: String?) { }
            override fun getAcceptedIssuers(): Array<X509Certificate> {
                return arrayOf()
            }
        }
        val trustManagers = arrayOf<TrustManager>(x509TrustManager)
        val sslContext = SSLContext.getInstance("TLS")
        sslContext.init(null, trustManagers, null)
        val builder = OkHttpClient.Builder()
        builder.sslSocketFactory(sslContext.socketFactory, x509TrustManager)
        builder.hostnameVerifier { _, _ -> true }
        return builder.build()
    }

    @Provides
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


    @Provides
    @Singleton
    fun provideIXGSDKManager(registrationManager: IRegistrationManager): IRegistrationManager = registrationManager
    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): IIXGAPIService = retrofit.create(IIXGAPIService::class.java)

    @Provides
    @Singleton
    fun provideIXGSDKDataStore(ixgDataStore: IXGSDKDataStore): IIXGSDKDataStore = ixgDataStore


}