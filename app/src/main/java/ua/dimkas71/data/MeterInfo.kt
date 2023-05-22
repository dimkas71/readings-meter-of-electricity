package ua.dimkas71.data

data class MeterInfo(
    val uuid: String,
    val factory: String,
    val currentValue: Int,
    val meterContract: String = "",
    val balance: Float = 0.0f,
    val checkingDate: String? = null,

    val isOff: Boolean = false
    )
