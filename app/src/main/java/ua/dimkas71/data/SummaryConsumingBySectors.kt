package ua.dimkas71.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ua.dimkas71.data.source.local.LocalSummaryConsumingBySectors
import ua.dimkas71.usecase.DateConverters

@Serializable
data class SummaryConsumingBySectors(
    @SerialName("sectorNumber")
    val sector: Int,
    @SerialName("consuming")
    val consumed: Long
    )

fun SummaryConsumingBySectors.asLocalSummaryBySectors(period: String): LocalSummaryConsumingBySectors {
    return LocalSummaryConsumingBySectors(
        DateConverters.asLong(period),
        this.sector,
        this.consumed
    )
}

fun List<SummaryConsumingBySectors>.asLocal(period: String): List<LocalSummaryConsumingBySectors> =
    this.map { LocalSummaryConsumingBySectors(
        DateConverters.asLong(period),
        it.sector,
        it.consumed
    ) }