package ua.dimkas71.data

import kotlinx.coroutines.flow.Flow


interface SummaryConsumingBySectorsRepository {

    fun getSummaryConsuming(): Flow<List<SummaryConsumingBySectors>>

}