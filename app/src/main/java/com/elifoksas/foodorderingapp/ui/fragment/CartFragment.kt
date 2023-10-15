package com.elifoksas.foodorderingapp.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.elifoksas.foodorderingapp.databinding.FragmentCartBinding
import com.elifoksas.foodorderingapp.ui.adapter.CartAdapter
import com.elifoksas.foodorderingapp.ui.viewmodel.CartViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CartFragment : Fragment() {
    private lateinit var binding:FragmentCartBinding
    private lateinit var viewModel: CartViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel:CartViewModel by viewModels()
        viewModel=tempViewModel
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =  FragmentCartBinding.inflate(inflater, container, false)

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())

        viewModel.cardFoodList.observe(viewLifecycleOwner){
            val cartAdapter = CartAdapter(requireContext(),it,viewModel)
            binding.recyclerView.adapter = cartAdapter
        }


        return binding.root
    }

    override fun onResume() {
        super.onResume()
        viewModel.loadCart("elif_oksas")
    }
}