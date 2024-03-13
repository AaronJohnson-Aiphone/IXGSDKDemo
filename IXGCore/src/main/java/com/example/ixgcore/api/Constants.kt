package com.example.ixgcore.api

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class Constants {
    val sys = "3"
    val sysver = "1.2"

    fun getSidFromDate(): String {
        return SimpleDateFormat("yyyyMMddHHmmssSSSSSS", Locale.getDefault()).format(Date())
    }
}