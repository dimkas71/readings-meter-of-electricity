package ua.dimkas71.data.repository

import ua.dimkas71.data.SummaryConsumingBySectors

interface SummaryConsumingBySectorsRepository {
    suspend fun getSummaryByPeriod(period: String): List<SummaryConsumingBySectors>
}