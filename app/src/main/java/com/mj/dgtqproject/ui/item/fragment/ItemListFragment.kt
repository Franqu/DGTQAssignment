package com.mj.dgtqproject.ui.item.fragment


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.mj.dgtqproject.databinding.FragmentItemListBinding
import com.mj.dgtqproject.ui.item.viewmodel.ItemViewModel
import com.mj.dgtqproject.ui.item.adapter.ItemAdapter
import com.mj.dgtqproject.ui.item.layoutmanager.HorizontalGridLayoutManager


class ItemListFragment : Fragment() {
    private lateinit var binding: FragmentItemListBinding
    private lateinit var viewModel: ItemViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View {
        binding = FragmentItemListBinding.inflate(inflater, container, false)

        binding.rvItems.layoutManager = HorizontalGridLayoutManager(requireContext(),1)



        viewModel.itemList.observe(viewLifecycleOwner) {
            val itemAdapter = ItemAdapter(requireContext(), it)
            binding.rvItems.adapter = itemAdapter
        }
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val itemViewModel: ItemViewModel by viewModels()
        viewModel = itemViewModel
    }


}