package com.mj.dgtqproject.ui.item.layoutmanager

import android.content.Context
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class HorizontalGridLayoutManager(context: Context, columnCount: Int, rowCount: Int, reverseLayout: Boolean) :
    GridLayoutManager(context, columnCount, RecyclerView.HORIZONTAL, reverseLayout) {

    override fun supportsPredictiveItemAnimations(): Boolean {
        return true
    }


}