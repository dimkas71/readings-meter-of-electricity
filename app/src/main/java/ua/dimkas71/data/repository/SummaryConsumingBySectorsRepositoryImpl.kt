package ua.dimkas71.data.repository

import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import ua.dimkas71.data.SummaryConsumingBySectors
import ua.dimkas71.data.asLocal
import ua.dimkas71.data.source.local.LocalSummaryConsumingBySectorsDao
import ua.dimkas71.data.source.local.asModel
import ua.dimkas71.data.source.network.SummaryConsumingBySectorsRemoteDataSource
import ua.dimkas71.usecase.DateConverters
import javax.inject.Inject

class SummaryConsumingBySectorsRepositoryImpl @Inject constructor(
    private val localDataSource: LocalSummaryConsumingBySectorsDao,
    private val remoteDataSource: SummaryConsumingBySectorsRemoteDataSource
) : SummaryConsumingBySectorsRepository {


    private val lock = Mutex()

    override suspend fun getSummaryByPeriod(period: String): List<SummaryConsumingBySectors> {
        var localData = localDataSource.getSummaryByPeriod(DateConverters.asLong(period))

        if (localData.isEmpty()) {
            val remoteConsuming = remoteDataSource.getSummaryConsuming(period)
            localDataSource.insertAll(remoteConsuming.asLocal(period))
            lock.withLock {
                localData = localDataSource.getSummaryByPeriod(DateConverters.asLong(period))
            }
        }

        return lock.withLock {
            localData.asModel(period)
        }
    }
}