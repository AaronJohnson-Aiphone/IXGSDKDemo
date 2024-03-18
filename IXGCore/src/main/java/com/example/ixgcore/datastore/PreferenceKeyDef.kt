package com.example.ixgcore.datastore

import androidx.datastore.preferences.core.stringPreferencesKey

object PreferenceKeyDef {
    val SERVER_URL =    stringPreferencesKey("server_url")
    val NAME =          stringPreferencesKey("name")
    val PROPERTY_ID =   stringPreferencesKey("property_id")
    val QR_CODE =       stringPreferencesKey("qr_code")
    val APP_SLOT_ID =   stringPreferencesKey("app_slot_id")
    val SECRET_KEY =    stringPreferencesKey("secret_key")
    val CERT =          stringPreferencesKey("cert")
}