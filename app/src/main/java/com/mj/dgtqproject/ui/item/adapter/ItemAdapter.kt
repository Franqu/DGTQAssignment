package com.mj.dgtqproject.ui.item.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mj.dgtqproject.data.item.model.Item
import com.mj.dgtqproject.databinding.ItemLayoutBinding
import com.mj.dgtqproject.ui.item.fragment.ItemLayoutProperties

class ItemAdapter(
    private var context: Context,
    var itemList: List<Item>,
    private val itemLayoutProperties: ItemLayoutProperties
) : RecyclerView.Adapter<ItemAdapter.CardViewHolder>() {

    private var currentPage = 0
    private val itemsPerPage = itemLayoutProperties.pageSize

    inner class CardViewHolder(var view: ItemLayoutBinding) : RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val binding = ItemLayoutBinding.inflate(LayoutInflater.from(context), parent, false)
        return CardViewHolder(binding)
    }

    override fun getItemCount(): Int {
        val startIndex = currentPage * itemsPerPage
        return if (startIndex + itemsPerPage <= itemList.size) itemsPerPage else itemList.size - startIndex
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        val positionRearrangedToFirstlyFillWholeColumn =
            getPositionRearrangedToFirstlyFillWholeColumn(position)
        if (positionRearrangedToFirstlyFillWholeColumn >= itemList.size) return
        val product = itemList[positionRearrangedToFirstlyFillWholeColumn]
        val view = holder.view

        view.tvName.text = product.name
    }

    fun loadPage(page: Int) {
        currentPage = page
        notifyDataSetChanged()
    }

    fun getPositionRearrangedToFirstlyFillWholeColumn(position: Int): Int {
        val totalItems = itemList.size
        val totalFullPages = totalItems / itemsPerPage
        val itemsOnLastPage = totalItems % itemsPerPage

        return if (currentPage < totalFullPages || itemsOnLastPage == 0) {
            getPositionRearrangedWhenThereAreNoItemsOnLastPage(position)
        } else {
            getPositionRearrangedWhenThereAreItemsOnLastPage(position, itemsOnLastPage)
        }
    }

    private fun getPositionRearrangedWhenThereAreItemsOnLastPage(
        position: Int,
        itemsOnLastPage: Int
    ): Int {
        val row = position / (itemsOnLastPage / itemLayoutProperties.columnCount)
        val column = position % (itemsOnLastPage / itemLayoutProperties.columnCount)
        return currentPage * itemsPerPage + column * (itemsOnLastPage / itemLayoutProperties.columnCount) + row
    }

    private fun getPositionRearrangedWhenThereAreNoItemsOnLastPage(position: Int): Int {
        val row = position / itemLayoutProperties.columnCount
        val column = position % itemLayoutProperties.columnCount
        return currentPage * itemsPerPage + column * (itemsPerPage / itemLayoutProperties.columnCount) + row
    }
}