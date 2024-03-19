package com.example.ixgcore.api

class Station(
    val number: Int,
    val name: String,
    val type: StationType,
    val canUnlock: Boolean,
    var hasVideo: Boolean = when (type) {
            StationType.DA ->           true
            StationType.BA ->           false
            StationType.MV ->           true
            StationType.MV7 ->          true
            StationType.RS ->           false
            StationType.MA ->           false
            StationType.SPMIC ->        false
            StationType.DV ->           false
            StationType.SSA ->          false
            StationType.SS_2G ->        false
            StationType.EA ->           true
            StationType.FA ->           false
            StationType.IP_TELEPHONE -> false
            StationType.C7 ->           false
            StationType.DM7 ->          true
            StationType.MK ->           false
            StationType.LC ->           false
            StationType.GW ->           false
            StationType.ASP ->          false
            StationType.DVM ->          true
        else -> false
    }
) {
}

enum class StationType(val value: Int) {
    DA(1),
    BA(2),
    MV(3),             // Unsupported
    MV7(4),
    RS(5),
    MA(6),             // Unsupported
    SPMIC(7),          // Unsupported
    DV(8),
    SSA(9),
    SS_2G(10),         //SS-2G
    EA(11),
    FA(12),
    IP_TELEPHONE(13), // Unsupported
    C7(14),           // Unsupported
    DM7(15),
    MK(16),           // Unsupported
    LC(17),           // Unsupported
    GW(18),           // Unsupported
    ASP(19),          // Unsupported
    DVM(20),
    NOT_RECOGNIZED(-1);

    companion object {
        private val VALUES = values()
        fun getByValue(value: Int) = VALUES.firstOrNull { it.value == value } ?: NOT_RECOGNIZED
    }
}

/*
Definition of RLS

(Unlock)
0 = release no
1 = release yes

*/

/*
 Definition of MEDIATYPE

 1 = Audio only
 2 = Video only
 3 = Audio + Video

 */