package com.mj.dgtqproject.ui.item.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mj.dgtqproject.data.item.model.Item
import com.mj.dgtqproject.databinding.ItemLayoutBinding

class ItemAdapter(var context: Context, var itemList: List<Item>) :
    RecyclerView.Adapter<ItemAdapter.CardViewHolder>() {

    inner class CardViewHolder(var view: ItemLayoutBinding) : RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val binding = ItemLayoutBinding.inflate(LayoutInflater.from(context), parent, false)
        return CardViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return itemList.size

    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        val product = itemList[position]
        val view = holder.view

        view.tvName.text = product.name
    }
}