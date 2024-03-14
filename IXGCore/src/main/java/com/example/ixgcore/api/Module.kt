package com.example.ixgcore.api

import android.util.Log
import com.example.ixgcore.datastore.IIXGSDKDataStore
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

class Module {
    //private val baseURL = "https://api-ixg1-r2.ixg.aiphone-app.net/"// Phase 1A
    private val baseURL = "https://api-ixg3-r2.ixg.aiphone-app.net"
    private val client = OkHttpClient.Builder()
        .addInterceptor(Module)
        .build()

    private val retrofitACL = Retrofit.Builder()
        .client(client)
        .baseUrl(baseURL)
        .addConverterFactory(ScalarsConverterFactory.create())
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    val retrofitServiceACL: IService by lazy {
        retrofitACL.create(IService::class.java)
    }

    val retrofitDataStoreACL: IIXGSDKDataStore by lazy {
        retrofitACL.create(IIXGSDKDataStore::class.java)
    }

    companion object LoggingInterceptor: Interceptor {

        override fun intercept(chain: Interceptor.Chain): Response {
            // request-----------------------------------------------
            val request: Request = chain.request()

            // request body formatting for logging
            val buf = okio.Buffer()
            request.body()?.writeTo(buf)
            val requestString = buf.readUtf8()
            val formattedRequest = requestString.replace("{", "{\n").replace(",", ",\n").replace("}", "\n}")
            Log.i("OKHTTP_LOG", "Sending request ${request.url()} with payload: $formattedRequest")

            // response----------------------------------------------
            val response = chain.proceed(request)

            // response body formatting for logging
            val responseBody = response.peekBody(Long.MAX_VALUE)
            val formattedResponse = responseBody.string().replace("{", "{\n").replace(",", ",\n").replace("}", "\n}")
            Log.i("OKHTTP_LOG", "Received response for ${response.request().url()}, code: ${response.code()} body: $formattedResponse")

            return response
        }
    }
}