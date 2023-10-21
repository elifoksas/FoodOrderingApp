package com.elifoksas.foodorderingapp.ui.fragment

import android.app.AlertDialog
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.elifoksas.foodorderingapp.R
import com.elifoksas.foodorderingapp.data.entity.CartFoods
import com.elifoksas.foodorderingapp.databinding.FragmentCartBinding
import com.elifoksas.foodorderingapp.ui.adapter.CartAdapter
import com.elifoksas.foodorderingapp.ui.viewmodel.CartViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CartFragment : Fragment() {
    private lateinit var binding:FragmentCartBinding
    private val viewModel: CartViewModel by viewModels()

    private var foodList : List<CartFoods> = listOf()

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

            if (it != null) {
                foodList = it
            }

        }

        viewModel.totalPrice.observe(viewLifecycleOwner){
            binding.totalPriceText.text = "₺${it.toString()}"
        }

        binding.orderButton.setOnClickListener {
            val builder = AlertDialog.Builder(context)
            builder.setTitle("Siparişi Onaylıyor musunuz?")
            builder.setPositiveButton("Evet") { dialog, which ->
                dialog.dismiss()
                showCongratsDialog()
                deleteOrders()
            }
            builder.setNegativeButton("Hayır") { dialog, which ->

                dialog.dismiss()
            }
            val dialog = builder.create()
            dialog.show()
        }



        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner){
            findNavController().popBackStack(R.id.homePageFragment,false)
        }


        return binding.root
    }

    fun deleteOrders(){
        foodList.forEach {
            viewModel.deleteFood(it.cart_food_id, it.username)
        }
    }
    private fun showCongratsDialog() {
        val congratsDialogBuilder = AlertDialog.Builder(context)
        congratsDialogBuilder.setTitle("Tebrikler")
        congratsDialogBuilder.setMessage("Sipariş verdiniz!")

        val congratsDialog = congratsDialogBuilder.create()
        congratsDialog.show()

        val handler = Handler()
        val runnable = Runnable {
            congratsDialog.dismiss()
        }
        handler.postDelayed(runnable, 3000)
    }

}