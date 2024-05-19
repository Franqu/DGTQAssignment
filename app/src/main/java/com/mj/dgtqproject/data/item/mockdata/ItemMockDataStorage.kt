package com.mj.dgtqproject.data.item.mockdata

import com.mj.dgtqproject.data.item.model.Item
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ItemMockDataStorage {
    private val itemList = ArrayList<Item>()
    suspend fun addItemsToList(): List<Item> = withContext(Dispatchers.IO) {

        for (i in 1..102 ){
            itemList.add(Item("Item$i"))
        }

        return@withContext itemList
    }
}