package com.mj.dgtqproject.ui.item.snaphelper

import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.mj.dgtqproject.ui.item.adapter.ItemAdapter

class ItemSnapHelper(private val pageSize: Int) : LinearSnapHelper() {

    private var recyclerView: RecyclerView? = null

    override fun attachToRecyclerView(recyclerView: RecyclerView?) {
        super.attachToRecyclerView(recyclerView)
        this.recyclerView = recyclerView
    }

    override fun findTargetSnapPosition(
        layoutManager: RecyclerView.LayoutManager,
        velocityX: Int,
        velocityY: Int
    ): Int {
        val snapPosition = super.findTargetSnapPosition(layoutManager, velocityX, velocityY)
        loadNextOrPreviousPage(snapPosition, velocityX)
        return snapPosition
    }

    private fun loadNextOrPreviousPage(snapPosition: Int, velocityX: Int) {
        val adapter = recyclerView?.adapter as? ItemAdapter
        val realPosition = adapter?.getRealPosition(snapPosition ?: 0)
        val minPage = 0
        var maxPage = getMaxPage(adapter)
        val newPage = if (velocityX > 0) realPosition?.div(pageSize)?.plus(1)
        else realPosition?.div(pageSize)?.minus(1)
        if (newPage in minPage..maxPage) adapter?.loadPage(newPage ?: 0)
    }

    private fun getMaxPage(adapter: ItemAdapter?): Int {
        var maxPage = (adapter?.itemList?.size?.div(pageSize)) ?: 0
        if (adapter?.itemList?.size?.mod(pageSize) == 0)
            maxPage -= 1
        if (maxPage < 0)
            maxPage = 0
        return maxPage
    }
}