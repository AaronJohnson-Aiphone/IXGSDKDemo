package com.example.ixgcore.api
import com.example.ixgcore.api.data.AddressBookRequestWrapper
import com.example.ixgcore.api.data.CheckStatusRequestWrapper
import com.example.ixgcore.api.data.DeregisterRequestWrapper
import com.example.ixgcore.api.data.QRRequestWrapper
import com.example.ixgcore.api.data.RegisterRequestWrapper
import com.example.ixgcore.api.data.RenameRequestWrapper
import com.example.ixgcore.api.data.SetStatusRequestWrapper
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface IService {

    //registration Management--------------------------------------
    @POST("getRoomApp")
    suspend fun sendQRCode(
        @Body qrWrapper: QRRequestWrapper
    ): Response<String>

    @POST("regAppClient")
    suspend fun regAppClient(
        @Body registerRequestDataWrapper: RegisterRequestWrapper
    ): Response<String>

    @POST("renameAppClient")
    suspend fun renameAppClient(
        @Body renameWrapper: RenameRequestWrapper
    ): Response<String>

    @POST("unregAppClient")
    suspend fun unregAppClient(
        @Body deregisterRequestDataWrapper: DeregisterRequestWrapper
    ): Response<String>

    @POST("checkConsentRoom")
    suspend fun checkConsentRoom(
        @Body statusRequestDataWrapper: CheckStatusRequestWrapper
    ): Response<String>

    @POST("setConsentInfo")
    suspend fun setConsentInfo(
        @Body setStatusRequestDataWrapper: SetStatusRequestWrapper
    ): Response<String>

    //Station Management------------------------------------------------
    @POST("getAddressBook")
    suspend fun getAddressBook(
        @Body addressBookRequestWrapper: AddressBookRequestWrapper
    ): Response<String>
}