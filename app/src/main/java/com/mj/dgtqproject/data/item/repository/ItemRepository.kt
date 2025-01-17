package com.mj.dgtqproject.data.item.repository

import com.mj.dgtqproject.data.item.mockdata.ItemMockDataStorage
import com.mj.dgtqproject.data.item.model.Item

class ItemRepository {
    private var itemMockDataStorage = ItemMockDataStorage()

    suspend fun addItemsToList() : MutableList<Item> = itemMockDataStorage.addItemsToList()
}