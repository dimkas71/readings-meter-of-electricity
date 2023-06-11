package ua.dimkas71.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Upsert


@Dao
interface LocalSummaryConsumingBySectorsDao {
    @Query("select * from summary_consuming_by_sectors where period = :period order by sector")
    suspend fun getSummaryByPeriod(period: Int): List<LocalSummaryConsumingBySectors>

    @Upsert
    suspend fun insert(summary: LocalSummaryConsumingBySectors)

    @Query("delete from summary_consuming_by_sectors")
    suspend fun deleteAll()
}