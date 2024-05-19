package com.mj.dgtqproject.ui.item.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mj.dgtqproject.data.item.model.Item
import com.mj.dgtqproject.data.item.repository.ItemRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class ItemViewModel : ViewModel() {
    private var itemRepository = ItemRepository()
    var itemList = MutableLiveData<List<Item>>()

    init {
        addItemsToList()

    }

    private fun addItemsToList() {
        CoroutineScope(Dispatchers.Main).launch {
            itemList.value = itemRepository.addItemsToList()

        }
    }
}