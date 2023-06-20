package ua.dimkas71.main.usecase.test

import org.junit.Assert.assertEquals
import org.junit.Test
import ua.dimkas71.usecase.DateConverters

class DateConvertersTest {

    @Test
    fun firstJanuary1970ZeroShouldBeReturned() {
        assertEquals(0L, DateConverters.asLong("19700101"))
    }

    @Test
    fun firstJanuary2023_1672531200000_ShouldBeReturned() {
        assertEquals(1672531200000L, DateConverters.asLong("20230101"))
    }

    @Test
    fun asStringTestFirstJanuary1970() {
        assertEquals("19700101", DateConverters.asString(0L))
    }

    @Test
    fun asStringTestJanuary2023() {
        assertEquals("20230101", DateConverters.asString(1672531200000L))
    }
}