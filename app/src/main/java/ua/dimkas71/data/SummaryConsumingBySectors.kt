package ua.dimkas71.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SummaryConsumingBySectors(
    @SerialName("sectorNumber")
    val sector: Int,
    @SerialName("consuming")
    val consumed: Double
    )
