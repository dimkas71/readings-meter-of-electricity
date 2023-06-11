package ua.dimkas71.usecase

import java.text.SimpleDateFormat

object DateConverters {

    /**
     * @param period - period in the format "dd.MM.yyyy"
     * @return count of milliseconds from 1970-01-01 - UTC date in milliseconds from 1
     */
    fun asLong(period: String): Long {
        val formatter = SimpleDateFormat("dd.MM.yyyy")
        return formatter.parse(period).let {
            it.time
        } ?: 0L
    }
}