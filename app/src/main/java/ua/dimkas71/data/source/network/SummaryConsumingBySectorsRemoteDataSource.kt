package ua.dimkas71.data.source.network

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ua.dimkas71.data.SummaryConsumingBySectors
import ua.dimkas71.rest.CounterServiceApi
import javax.inject.Inject

class SummaryConsumingBySectorsRemoteDataSource @Inject constructor(private val api: CounterServiceApi) {
    suspend fun getSummaryConsuming(period: String): List<SummaryConsumingBySectors> {
        val consuming = withContext(Dispatchers.Default) {
            api.getSummaryConsuming(period)
        }
        return consuming
    }
}