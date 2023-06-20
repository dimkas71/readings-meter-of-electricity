package ua.dimkas71.data.source.local

import androidx.annotation.NonNull
import androidx.room.Entity
import ua.dimkas71.data.SummaryConsumingBySectors
import ua.dimkas71.usecase.DateConverters

@Entity(
    tableName = "summary_consuming_by_sectors",
    primaryKeys = ["period", "sector"]
)
data class LocalSummaryConsumingBySectors(
    @NonNull val period: Long,
    @NonNull val sector: Int,
    @NonNull val consumed: Long
)

fun List<LocalSummaryConsumingBySectors>.asModel(period: String): List<SummaryConsumingBySectors> =
    this.filter{ it.period == DateConverters.asLong(period) }.map {
        SummaryConsumingBySectors(
            it.sector,
            it.consumed
        )
    }
