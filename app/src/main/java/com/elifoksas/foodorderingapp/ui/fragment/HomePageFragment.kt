package com.elifoksas.foodorderingapp.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.elifoksas.foodorderingapp.databinding.FragmentHomePageBinding
import com.elifoksas.foodorderingapp.ui.adapter.FoodsAdapter
import com.elifoksas.foodorderingapp.ui.viewmodel.HomePageViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomePageFragment : Fragment() {

    private lateinit var binding: FragmentHomePageBinding
    private lateinit var viewModel: HomePageViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel:HomePageViewModel by viewModels()
        viewModel = tempViewModel
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomePageBinding.inflate(inflater, container, false)

        val layoutManager = GridLayoutManager(requireContext(), 2)
        binding.rv.layoutManager = layoutManager

        viewModel.foodsList.observe(viewLifecycleOwner){
            val foodsAdapter = it?.let { it1 -> FoodsAdapter(requireContext(), it1,viewModel) }
            binding.rv.adapter = foodsAdapter
        }

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String): Boolean {
                viewModel.filterFoods(p0)
                return true
            }

            override fun onQueryTextChange(p0: String): Boolean {
                viewModel.filterFoods(p0)
                return false
            }
        })

        binding.searchView.setOnCloseListener(object : SearchView.OnCloseListener {
            override fun onClose(): Boolean {
                viewModel.foodsList.observe(viewLifecycleOwner){
                    val foodsAdapter = it?.let { it1 -> FoodsAdapter(requireContext(), it1,viewModel) }
                    binding.rv.adapter = foodsAdapter
                }
                viewModel.loadFoods()
                return true
            }

        })


        return  binding.root
    }

    private fun observeFoodList(){

    }

    override fun onResume() {
        super.onResume()
        viewModel.loadFoods()
    }
}