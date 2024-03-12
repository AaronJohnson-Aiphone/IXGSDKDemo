package com.example.ixgcore.api
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Url

interface IIXGAPIService {
    @Headers(
        "Accept: */*",
        "Content-Type: application/json"
    )
    @POST
    suspend fun sendQRCode(
        @Url apiUrl: String,
        @Body qrData: QRRequestData
    ): Response<String>


    companion object {
        fun create(baseUrl: String): IIXGAPIService {
            val retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(IIXGAPIService::class.java)
        }
    }
}