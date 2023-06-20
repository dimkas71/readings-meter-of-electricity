package ua.dimkas71.usecase

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

object DateConverters {

    /**
     * @param period - period in the format "dd.MM.yyyy"
     * @return count of milliseconds from 1970-01-01 - UTC date in milliseconds from 1
     */
    fun asLong(period: String): Long {
        val formatter = SimpleDateFormat("yyyyMMdd").apply {
            timeZone = TimeZone.getTimeZone("UTC")
        }
        return formatter.parse(period).let {
            it.time
        } ?: 0L
    }

    fun asString(period: Long): String {
        val formatter = SimpleDateFormat("yyyyMMdd").apply {
            timeZone = TimeZone.getTimeZone("UTC")
        }
        return formatter.format(Date(period))
    }
}