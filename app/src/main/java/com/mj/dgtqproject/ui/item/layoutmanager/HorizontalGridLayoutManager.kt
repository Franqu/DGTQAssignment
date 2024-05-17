package com.mj.dgtqproject.ui.item.layoutmanager

import android.content.Context
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class HorizontalGridLayoutManager(context: Context, spanCount: Int) :
    GridLayoutManager(context, spanCount, RecyclerView.HORIZONTAL, false) {

    override fun supportsPredictiveItemAnimations(): Boolean {
        return true
    }
}