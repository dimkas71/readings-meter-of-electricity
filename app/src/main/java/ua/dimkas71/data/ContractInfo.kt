package ua.dimkas71.data

data class ContractInfo(
    val contractNumber: String,
    val owner: String,
    val meterInfo: List<MeterInfo> = listOf()
)
