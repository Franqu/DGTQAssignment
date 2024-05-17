package com.mj.dgtqproject.item

import com.mj.dgtqproject.data.item.model.Item
import org.junit.Assert
import org.junit.Test

class ItemUnitTest {
    @Test
    fun nameIsCorrect() {
        val item = Item("Skoda")
        Assert.assertEquals("Skoda", item.name)
    }
}