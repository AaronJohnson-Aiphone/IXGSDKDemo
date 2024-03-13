package com.example.ixgcore.api
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface IIXGAPIService {

    //registration------------------------------------------------
    @POST("getRoomApp")
    suspend fun sendQRCode(
        @Body qrWrapper: QRRequestWrapper
    ): Response<String>

    @POST("regAppClient")
    suspend fun regAppClient(
        @Body registerRequestDataWrapper: RegisterRequestDataWrapper
    ): Response<String>

    @POST("renameAppClient")
    suspend fun renameAppClient(
        @Body renameWrapper: RenameRequestDataWrapper
    ): Response<String>

    @POST("unregAppClient")
    suspend fun unregAppClient(
        @Body deregisterRequestDataWrapper: DeregisterRequestDataWrapper
    ): Response<String>

    @POST("checkConsentRoom")
    suspend fun checkConsentRoom(
        @Body statusRequestDataWrapper: StatusRequestDataWrapper
    ): Response<String>


    //station management---------------------------------------
    @POST("getRoomApp")
    suspend fun getAddressBook(
        @Body qrWrapper: QRRequestWrapper
    ): Response<String>
}