package com.example.ixgcore.api.data

import com.squareup.moshi.Json


data class ACLAddressBookData(
    @Json(name = "CALLTONO")     val stationNumbers: List<String>,
    @Json(name = "CALLTONM")     val stationNames: List<String>,
    @Json(name = "TYPE")         val stationTypes:List<Int>,
    @Json(name = "UNLOCK")       val unlockAuthorization: List<Int>,
    @Json(name = "SOUNDCODEC")   val soundCodec: List<Int>,
)