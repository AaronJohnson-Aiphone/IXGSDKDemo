package com.example.ixgcore.api
import com.example.ixgcore.api.data.DeregisterRequestDataWrapper
import com.example.ixgcore.api.data.QRRequestWrapper
import com.example.ixgcore.api.data.RegisterRequestDataWrapper
import com.example.ixgcore.api.data.RenameRequestDataWrapper
import com.example.ixgcore.api.data.StatusRequestDataWrapper
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface IService {

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
}