package com.elifoksas.foodorderingapp.ui.fragment


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.elifoksas.foodorderingapp.data.entity.Foods
import com.elifoksas.foodorderingapp.databinding.FragmentDetailsBinding
import com.elifoksas.foodorderingapp.ui.viewmodel.DetailsViewModel
import com.elifoksas.foodorderingapp.utils.gecis
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : Fragment() {
    private lateinit var binding: FragmentDetailsBinding
    private lateinit var viewModel: DetailsViewModel

    private lateinit var chosenFood: Foods
    private var quantity: Int = 1
    private var totalPrice: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel:DetailsViewModel by viewModels()
        viewModel = tempViewModel
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDetailsBinding.inflate(inflater, container, false)

        val bundle:DetailsFragmentArgs by navArgs()
        chosenFood = bundle.food
        totalPrice = chosenFood.food_price

        updateUI()
        updatePrice()
        addToCart(chosenFood.food_name,chosenFood.food_image_name,chosenFood.food_price,quantity,"elif_oksas")

        return binding.root
    }

    private fun updatePrice(){
        // Update product count and total price on plus button click
        binding.plusButton.setOnClickListener{
            quantity++
            totalPrice = chosenFood.food_price * quantity
            updateUI()
        }

        // Update number of products and total price on click of minus button
        binding.minusButton.setOnClickListener {
            if (quantity > 1) {
                quantity--
                totalPrice = chosenFood.food_price * quantity
                updateUI()
            }
        }
    }
    private fun updateUI(){
        binding.foodName.text = chosenFood.food_name
        binding.foodPrice.text = "₺${chosenFood.food_price}"

        val url = "http://kasimadalan.pe.hu/yemekler/resimler/${chosenFood.food_image_name}"
        Glide.with(this).load(url).into(binding.foodImage)

        binding.quantityText.text = quantity.toString()
        binding.totalPriceText.text = "₺$totalPrice"
    }

    private fun addToCart(food_name : String,
                          food_image_name : String,
                          food_price : Int,
                          food_quantity : Int,
                          username : String){
        binding.addToCartButton.setOnClickListener {
            viewModel.addToCart(food_name,food_image_name,food_price,food_quantity,username)
            val gecis = DetailsFragmentDirections.actionDetailsFragmentToCartFragment()
            Navigation.gecis(it,gecis)
        }
    }


}