package com.example.ixgcore.api.data

import com.squareup.moshi.Json

data class AddressBookRequestWrapper(
    @Json(name = "BODY")         val addressBookRequestData: AddressBookRequestData
)

data class AddressBookRequestData (
    @Json(name = "SID")          val sid: String,
    @Json(name = "SYS")          val sys: String,
    @Json(name = "PROPID")       val propertyID: String,
    @Json(name = "CLIID")        val clientID: String,
    @Json(name = "REGCODE")      val registrationCode: String
)

data class AddressBookResponseWrapper(
    @Json(name = "BODY")          val addressBookResponseData: AddressBookResponseData
)

data class AddressBookResponseData(
    @Json(name = "ADDBOOK")        val addressBookData: ACLAddressBookData,
)
