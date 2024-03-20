package com.example.ixgcore.api

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class Constants {
    val sys = "3"
    val sysver = "1.2"
    val defaultServerURl = "https://api-ixg1-r2.ixg.aiphone-app.net"
    val osKind = "1"

//    val baseURL = "https://api-ixg1-r2.ixg.aiphone-app.net/"// Phase 1A
    val baseURL = "https://api-ixg3-r2.ixg.aiphone-app.net"

    fun getSidFromDate(): String {
        return SimpleDateFormat("yyyyMMddHHmmssSSSSSS", Locale.getDefault()).format(Date())
    }
}