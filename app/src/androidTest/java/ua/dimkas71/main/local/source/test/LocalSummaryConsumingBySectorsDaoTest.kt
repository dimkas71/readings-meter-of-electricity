package ua.dimkas71.main.local.source.test

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import ua.dimkas71.data.source.local.AppDatabase
import ua.dimkas71.data.source.local.LocalSummaryConsumingBySectors

class LocalSummaryConsumingBySectorsDaoTest {

    private lateinit var db: AppDatabase

    @Before
    fun setUp() {
        db = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).allowMainThreadQueries()
            .build()
    }

    @Test
    fun insertOneTest() = runTest {
        val bySectors = LocalSummaryConsumingBySectors(
            0,
            1,
            10000.0
        )
        db.getLocalSummaryConsumingBySectorsDao()
            .insert(bySectors)

        val consuming = db.getLocalSummaryConsumingBySectorsDao().getSummaryByPeriod(0)

        assertEquals(1, consuming.size)
        assertEquals(bySectors, consuming[0])

    }


    @Test
    fun insertAllTest() = runTest {

        val period: Long = 0
        val all = (1..8).map { sector ->
            LocalSummaryConsumingBySectors(
                period,
                sector,
                10000.0
            )
        }

        db.getLocalSummaryConsumingBySectorsDao().insertAll(all)

        val shouldBeEmpty = db.getLocalSummaryConsumingBySectorsDao().getSummaryByPeriod(1L)

        assertEquals(0, shouldBeEmpty.size)


        val recorded = db.getLocalSummaryConsumingBySectorsDao().getSummaryByPeriod(period)
        assertEquals(8, recorded.size)

    }

    @Test
    fun deleteAllTest() = runTest {
        val consuming = (1..8).map {
            LocalSummaryConsumingBySectors(
                0,
                it,
                10000.0
            )

        }

        db.getLocalSummaryConsumingBySectorsDao().insertAll(consuming)
        val recorded = db.getLocalSummaryConsumingBySectorsDao().getSummaryByPeriod(0)
        assertEquals(8, recorded.size)

        db.getLocalSummaryConsumingBySectorsDao().deleteAll()

        val shouldBeEmpty = db.getLocalSummaryConsumingBySectorsDao().getSummaryByPeriod(0L)

        assertEquals(0, shouldBeEmpty.size)

    }

}