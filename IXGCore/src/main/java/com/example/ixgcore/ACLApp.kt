package com.example.ixgcore

import kotlinx.serialization.Serializable

@Serializable
class ACLApp(
    val slot: String,
    val number: String,
    val names: Array<String>,
    val registrationStatus: String
) {
}