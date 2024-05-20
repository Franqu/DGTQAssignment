package com.mj.dgtqproject.ui.item.adapter

import com.mj.dgtqproject.data.item.model.Item


interface IItemAdapter {
    fun getItem(position: Int): Item
    fun removeItem(position: Int)
    fun addItem(position: Int, item: Item)
    fun getPositionRearrangedToFirstlyFillWholeColumn(position: Int): Int
}
