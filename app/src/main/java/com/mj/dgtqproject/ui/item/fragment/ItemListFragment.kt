package com.mj.dgtqproject.ui.item.fragment


import android.os.Bundle
import android.view.DragEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.mj.dgtqproject.databinding.FragmentItemListBinding
import com.mj.dgtqproject.ui.item.adapter.ItemAdapter
import com.mj.dgtqproject.ui.item.layoutmanager.HorizontalGridLayoutManager
import com.mj.dgtqproject.ui.item.snaphelper.ItemSnapHelper
import com.mj.dgtqproject.ui.item.viewmodel.ItemViewModel


private const val COLUMN_COUNT = 2
private const val ROW_COUNT = 5

class ItemListFragment : Fragment() {
    private lateinit var binding: FragmentItemListBinding
    private lateinit var viewModel: ItemViewModel
    private lateinit var viewModelBottom: ItemViewModel
    private val itemLayoutPropertiesTop = ItemLayoutProperties(ROW_COUNT, COLUMN_COUNT, false)
    private val itemLayoutPropertiesBottom = ItemLayoutProperties(ROW_COUNT, COLUMN_COUNT, false)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View {
        binding = FragmentItemListBinding.inflate(inflater, container, false)

        setUpTopView()
        setUpBottomView()

        return binding.root
    }

    private fun setUpTopView() {
        binding.rvItemsTop.layoutManager = HorizontalGridLayoutManager(
            requireContext(),
            COLUMN_COUNT, itemLayoutPropertiesTop.reverseLayout
        )

        val snapHelper = ItemSnapHelper(itemLayoutPropertiesTop)
        snapHelper.attachToRecyclerView(binding.rvItemsTop)

        binding.rvItemsTop.setOnDragListener(dragListener)

        viewModel.itemList.observe(viewLifecycleOwner) {
            val itemAdapterTop =
                ItemAdapter(requireContext(), it.toMutableList(), itemLayoutPropertiesTop)
            binding.rvItemsTop.adapter = itemAdapterTop
        }
    }

    private fun setUpBottomView() {
        binding.rvItemsBottom.layoutManager = HorizontalGridLayoutManager(
            requireContext(),
            COLUMN_COUNT, itemLayoutPropertiesBottom.reverseLayout
        )

        val snapHelper = ItemSnapHelper(itemLayoutPropertiesBottom)
        snapHelper.attachToRecyclerView(binding.rvItemsBottom)

        binding.rvItemsBottom.setOnDragListener(dragListener)

        viewModelBottom.itemList.observe(viewLifecycleOwner) {
            val itemAdapterBottom =
                ItemAdapter(requireContext(), it.toMutableList(), itemLayoutPropertiesBottom)
            binding.rvItemsBottom.adapter = itemAdapterBottom
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val itemViewModel: ItemViewModel by viewModels()
        viewModel = itemViewModel
        val itemViewModelBottom: ItemViewModel by viewModels()
        viewModelBottom = itemViewModelBottom
    }

    val dragListener = View.OnDragListener { targetView, event ->
        when (event.action) {
            DragEvent.ACTION_DROP -> {
                val originalView = event.localState as View

                val originalRv = originalView.parent as RecyclerView
                val targetRv = targetView as RecyclerView

                val originalPosition = originalView.tag as Int
                val targetPosition = targetRv.getChildAdapterPosition(originalView)

                val originalAdapter = originalRv.adapter as ItemAdapter
                val targetAdapter = targetRv.adapter as ItemAdapter

                if (originalRv != targetRv) {
                    val item = originalAdapter.getItem(originalPosition)
                    targetAdapter.addItem(targetPosition, item)
                    originalAdapter.removeItem(originalPosition)

                    /*[MJ] We have to notify both adapters to update the UI, even though it is notified in
                    addItem and removeItem methods, as it caused crashes when the user tried to drag and drop
                    due to asynchronous nature of the code.*/
                    targetAdapter.notifyDataSetChanged()
                    originalAdapter.notifyDataSetChanged()
                }
            }
        }
        true
    }
}