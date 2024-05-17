package com.mj.dgtqproject.data.item.mockdata

import com.mj.dgtqproject.data.item.model.Item
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ItemMockDataStorage {
    private val itemList = ArrayList<Item>()
    suspend fun addItemsToList(): List<Item> = withContext(Dispatchers.IO) {


        val i1 = Item("Skoda")
        val i2 = Item("Audi")
        val i3 = Item("BMW")
        val i4 = Item("Mercedes")
        val i5 = Item("Opel")
        val i6 = Item("Porsche")
        val i7 = Item("Ferrari")
        val i8 = Item("Volvo")
        val i9 = Item("Toyota")
        val i10 = Item("Honda")
        val i11 = Item("Jaguar")
        val i12 = Item("Lexus")
        val i13 = Item("Infiniti")

        itemList.add(i1)
        itemList.add(i2)
        itemList.add(i3)
        itemList.add(i4)
        itemList.add(i5)
        itemList.add(i6)
        itemList.add(i7)
        itemList.add(i8)
        itemList.add(i9)
        itemList.add(i10)
        itemList.add(i11)
        itemList.add(i12)
        itemList.add(i13)

        return@withContext itemList
    }
}