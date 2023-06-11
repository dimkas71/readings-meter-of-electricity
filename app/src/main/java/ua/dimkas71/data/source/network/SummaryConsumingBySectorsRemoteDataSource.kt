package ua.dimkas71.data.source.network

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ua.dimkas71.data.SummaryConsumingBySectors
import ua.dimkas71.rest.CounterServiceApi
import javax.inject.Inject

class SummaryConsumingBySectorsRemoteDataSource @Inject constructor(private val api: CounterServiceApi, private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO) {
    suspend fun getSummaryConsuming(period: String): List<SummaryConsumingBySectors> {
        val consuming = withContext(ioDispatcher) {
            api.getSummaryConsuming(period)
        }
        return consuming
    }
}