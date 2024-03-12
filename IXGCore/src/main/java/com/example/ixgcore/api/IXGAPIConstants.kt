package com.example.ixgcore.api

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class IXGAPIConstants {
    val sys = "3"
    val sysver = "1.2"
    val defaultServerURl = "https://api-ixg1-r2.ixg.aiphone-app.net"

    fun getSidFromDate(): String {
        return SimpleDateFormat("yyyyMMddHHmmssSSSSSS", Locale.getDefault()).format(Date())
    }
}