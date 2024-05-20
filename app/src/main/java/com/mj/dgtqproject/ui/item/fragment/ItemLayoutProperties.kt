package com.mj.dgtqproject.ui.item.fragment

data class ItemLayoutProperties(
    val rowCount: Int,
    val columnCount: Int,
    val reverseLayout: Boolean
) {
    val pageSize = rowCount * columnCount
}
