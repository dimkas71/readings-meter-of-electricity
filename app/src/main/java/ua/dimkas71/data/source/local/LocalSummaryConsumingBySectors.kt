package ua.dimkas71.data.source.local

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "summary_consuming_by_sectors",
    primaryKeys = ["period", "sector"]
)
data class LocalSummaryConsumingBySectors(
    @NonNull val period: Long,
    @NonNull val sector: Int,
    @NonNull val consumed: Double
)
