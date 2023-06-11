package ua.dimkas71.data

import kotlinx.coroutines.flow.Flow


interface SummaryConsumingBySectorsRepository {

    suspend fun getSummaryConsuming(): List<SummaryConsumingBySectors>

}