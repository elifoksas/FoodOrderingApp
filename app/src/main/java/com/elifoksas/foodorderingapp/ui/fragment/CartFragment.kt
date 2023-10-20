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
    private val viewModel: CartViewModel by viewModels()

    private var totalPrice:Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =  FragmentCartBinding.inflate(inflater, container, false)

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        viewModel.loadCart("elif_oksas")
        viewModel.cartFoodList.observe(viewLifecycleOwner){
            val cartAdapter = it?.let { it1 -> CartAdapter(requireContext(), it1,viewModel) }
            binding.recyclerView.adapter = cartAdapter
        }

        viewModel.totalPrice.observe(viewLifecycleOwner){
            binding.totalPriceText.text = "₺${it.toString()}"
        }


        return binding.root
    }

    override fun onResume() {
        super.onResume()
    }
}