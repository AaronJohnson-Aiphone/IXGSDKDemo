package com.example.ixgcore.api

enum class IXGAPIRoute(val api: String) {
    SEND_QR_CODE("/getRoomApp"), // returns the list of app slots for the unit
    REGISTER_APP("/regAppClient"), // registers the app to the provided app-slot
    DEREGISTER_APP("/unregAppClient"), // de-registers the app
    RENAME_APP("/renameAppClient"), // changes the name of the app client on the IXGPortal site
    GET_REGISTRATION_STATUS("/checkConsentRoom"),

    // calling
    MAKE_CALL("/makeCall"),
    RECEIVE_CALL("/receiveCall"),
    CALL_ANSWERED("/callAnswered"),
    END_CALL("/endCall"),
    CALL_ENDED_BY_STATION("/callEndedByStation"),

    // video playback
    GET_RECORDING("/getRecording"),
    GET_CALL_RECORDS("/getcallRecords"),

    // station management
    GET_STATIONS("/getAddressBook"),
    UNLOCK("/unlock"),
}