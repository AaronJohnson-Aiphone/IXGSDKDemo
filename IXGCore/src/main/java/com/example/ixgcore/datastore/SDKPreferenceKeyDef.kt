package com.example.ixgcore.datastore

import androidx.datastore.preferences.core.stringPreferencesKey

object SDKPreferenceKeyDef {
    val SERVER_URL = stringPreferencesKey("server_url")
    val NAME = stringPreferencesKey("name")
    val PROPERTY_ID = stringPreferencesKey("property_id")
    val QR_CODE = stringPreferencesKey("qr_code")
    val APP_SLOT_ID = stringPreferencesKey("app_slot_id")
}