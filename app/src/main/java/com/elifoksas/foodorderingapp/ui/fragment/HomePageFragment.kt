package com.elifoksas.foodorderingapp.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
            val foodsAdapter = FoodsAdapter(requireContext(),it,viewModel)
            binding.rv.adapter = foodsAdapter
        }


        return  binding.root
    }

    override fun onResume() {
        super.onResume()
        viewModel.loadFoods()
    }
}