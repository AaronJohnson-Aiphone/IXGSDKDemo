package com.example.ixgsdkdemo.network
//
//import android.util.Log
//import com.squareup.moshi.Json
//import com.squareup.moshi.JsonClass
//import com.squareup.moshi.Moshi
//import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
//import okhttp3.Interceptor
//import okhttp3.OkHttpClient
//import okhttp3.Request
//import okhttp3.Response
//import retrofit2.Retrofit
//import retrofit2.converter.moshi.MoshiConverterFactory
//import retrofit2.http.Body
//import retrofit2.http.POST
//
//private const val BASE_URL = "https://api-ixg1-r2.ixg.aiphone-app.net/"
//
//private val client = OkHttpClient.Builder()
//    .addInterceptor(LoggingInterceptor())
//    .build()
//
//private val moshi = Moshi.Builder()
//    .addLast(KotlinJsonAdapterFactory())
//    .build()
//
//private val retrofitACL = Retrofit.Builder()
//    .client(client)
//    .baseUrl(BASE_URL)
//    .addConverterFactory(MoshiConverterFactory.create(moshi))
//    .build()
//
//interface MyApiService {
//    @POST("getRoomApp")
//    suspend fun sendQRCode(
//        @Body body: RequestWrapper
//    ): BadResponseData
//}
//
//@JsonClass(generateAdapter = true)
//data class RequestWrapper(
//    @Json(name = "BODY") val wrapper: RequestData
//)
//
//@JsonClass(generateAdapter = true)
//data class RequestData (
//    @Json(name = "ROOMCODE") val roomCode: String,
//    @Json(name = "SID") val sid: String,
//    @Json(name = "SYS") val sys: String,
//    @Json(name = "SYSVER") val sysver: String
//)
//
//@JsonClass(generateAdapter = true)
//data class BadResponseData(
//    @Json(name = "message") val message: String
//)
//
//object MyApi {
//    val retrofitServiceACL: MyApiService by lazy {
//        retrofitACL.create(MyApiService::class.java)
//    }
//}
//
//class LoggingInterceptor: Interceptor {
//    override fun intercept(chain: Interceptor.Chain): Response {
//        val request: Request = chain.request()
//
//        val t1 = System.nanoTime()
//
//        Log.i("OKHTTP_LOG", "Sending request ${request.url()} on ${chain.connection()} ${request.headers()}")
//
//        val response = chain.proceed(request)
//
//        val t2 = System.nanoTime()
//        val delta = (t2 - t1) / 1e6
//        Log.i("OKHTTP_LOG", "Received response for ${response.request().url()}, $delta, ${response.headers()}")
//
//        return response
//    }
//
//}