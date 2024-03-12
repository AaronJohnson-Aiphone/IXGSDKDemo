package com.example.ixgcore.api
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface IIXGAPIService {

    @POST("getRoomApp")
    suspend fun sendQRCode(
        @Body qrWrapper: QRRequestWrapper
    ): Response<String>
}