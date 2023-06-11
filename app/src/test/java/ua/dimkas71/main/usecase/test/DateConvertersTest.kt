package ua.dimkas71.main.usecase.test

import org.junit.Assert.assertEquals
import org.junit.Test
import ua.dimkas71.usecase.DateConverters

class DateConvertersTest {

    @Test
    fun firstJanuary1970ZeroShouldBeReturned() {
        assertEquals(0L, DateConverters.asLong("01.01.1970"))
    }

    @Test
    fun firstJanuary20231672524000000ShouldBeReturned() {
        assertEquals(1672524000000L, DateConverters.asLong("01.01.2023"))
    }
}