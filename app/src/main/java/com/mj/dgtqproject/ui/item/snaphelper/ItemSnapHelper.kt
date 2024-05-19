package com.mj.dgtqproject.ui.item.snaphelper

import android.view.View
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView

class ItemSnapHelper(private val pageSize: Int) : LinearSnapHelper() {

    override fun findTargetSnapPosition(
        layoutManager: RecyclerView.LayoutManager,
        velocityX: Int,
        velocityY: Int
    ): Int {
        val centerView = findSnapView(layoutManager) ?: return RecyclerView.NO_POSITION
        val position = layoutManager.getPosition(centerView)
        var targetPosition = -1
        if (layoutManager.canScrollHorizontally()) {
            targetPosition = if (velocityX < 0) {
                position - pageSize
            } else {
                position + pageSize
            }
        }

        if (layoutManager.canScrollVertically()) {
            targetPosition = if (velocityY < 0) {
                position - pageSize
            } else {
                position + pageSize
            }
        }

        val firstItem = 0
        val lastItem = layoutManager.itemCount - 1
        targetPosition = Math.min(lastItem, Math.max(targetPosition, firstItem))
        return targetPosition
    }

    override fun calculateDistanceToFinalSnap(
        layoutManager: RecyclerView.LayoutManager,
        targetView: View
    ): IntArray? {
        return super.calculateDistanceToFinalSnap(layoutManager, targetView)
    }
}