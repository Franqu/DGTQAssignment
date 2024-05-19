package com.mj.dgtqproject.ui.item.snaphelper

import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView

class ItemSnapHelper(pageSize : Int) : LinearSnapHelper() {

    private val pageSize = pageSize

    override fun findTargetSnapPosition(
        layoutManager: RecyclerView.LayoutManager?,
        velocityX: Int,
        velocityY: Int
    ): Int {
        return super.findTargetSnapPosition(layoutManager, velocityX, velocityY)
    }
}