package ua.dimkas71.main

data class ContractInfo(
    val contractNumber: String,
    val owner: String,
    val meterInfo: List<MeterInfo> = listOf()
)
