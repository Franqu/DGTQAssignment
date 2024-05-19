package com.mj.dgtqproject.ui.item.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mj.dgtqproject.data.item.model.Item
import com.mj.dgtqproject.databinding.ItemLayoutBinding

class ItemAdapter(
    private var context: Context,
    var itemList: List<Item>,
    columnCount: Int,
    rowCount: Int
) : RecyclerView.Adapter<ItemAdapter.CardViewHolder>() {

    private var currentPage = 0
    private val itemsPerPage = columnCount * rowCount

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
        val realPosition = currentPage * itemsPerPage + position
        val product = itemList[realPosition]
        val view = holder.view

        view.tvName.text = product.name
    }

    fun loadPage(page: Int) {
        currentPage = page
        notifyDataSetChanged()
    }

    fun getRealPosition(position: Int): Int {
        return currentPage * itemsPerPage + position
    }
}