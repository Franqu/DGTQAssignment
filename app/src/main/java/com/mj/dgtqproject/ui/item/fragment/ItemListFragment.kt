package com.mj.dgtqproject.ui.item.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.ItemTouchHelper.SimpleCallback
import androidx.recyclerview.widget.RecyclerView
import com.mj.dgtqproject.databinding.FragmentItemListBinding
import com.mj.dgtqproject.ui.item.adapter.ItemAdapter
import com.mj.dgtqproject.ui.item.layoutmanager.HorizontalGridLayoutManager
import com.mj.dgtqproject.ui.item.snaphelper.ItemSnapHelper
import com.mj.dgtqproject.ui.item.viewmodel.ItemViewModel
import java.util.Collections


private const val COLUMN_COUNT = 2
private const val ROW_COUNT = 5
private const val PAGE_SIZE = COLUMN_COUNT * ROW_COUNT

class ItemListFragment : Fragment() {
    private lateinit var binding: FragmentItemListBinding
    private lateinit var viewModel: ItemViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View {
        binding = FragmentItemListBinding.inflate(inflater, container, false)

        binding.rvItems.layoutManager = HorizontalGridLayoutManager(requireContext(),
            COLUMN_COUNT, ROW_COUNT, false)
        val snapHelper = ItemSnapHelper(PAGE_SIZE)
        snapHelper.attachToRecyclerView(binding.rvItems)

        val itemTouchHelper = ItemTouchHelper(simpleCallback)
        itemTouchHelper.attachToRecyclerView(binding.rvItems)


        viewModel.itemList.observe(viewLifecycleOwner) {
            val itemAdapter = ItemAdapter(requireContext(), it , COLUMN_COUNT, ROW_COUNT)

            binding.rvItems.adapter = itemAdapter
        }
        return binding.root
    }

    var simpleCallback: SimpleCallback = object : SimpleCallback(
        ItemTouchHelper.UP or ItemTouchHelper.DOWN or ItemTouchHelper.START or ItemTouchHelper.END,
        0
    ) {
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {
            val fromPosition = viewHolder.adapterPosition
            val toPosition = target.adapterPosition
            viewModel.itemList.value?.let { Collections.swap(it, fromPosition, toPosition) }
            recyclerView.adapter!!.notifyItemMoved(fromPosition, toPosition)
            return false
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        }

        override fun isLongPressDragEnabled(): Boolean {
            return true
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val itemViewModel: ItemViewModel by viewModels()
        viewModel = itemViewModel
    }


}