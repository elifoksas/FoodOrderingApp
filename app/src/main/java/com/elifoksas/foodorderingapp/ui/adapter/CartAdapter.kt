package com.elifoksas.foodorderingapp.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.elifoksas.foodorderingapp.data.entity.CartFoods
import com.elifoksas.foodorderingapp.databinding.CartItemBinding
import com.elifoksas.foodorderingapp.ui.viewmodel.CartViewModel

class CartAdapter(var mContext: Context, var cartFoodsList:List<CartFoods>, var viewModel: CartViewModel)
    : RecyclerView.Adapter<CartAdapter.CartItemHolder>() {

        inner class CartItemHolder(var item:CartItemBinding) : RecyclerView.ViewHolder(item.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartItemHolder {
        val binding = CartItemBinding.inflate(LayoutInflater.from(mContext),parent,false)
        return CartItemHolder(binding)
    }
    private var quantity: Int = 1


    override fun getItemCount(): Int {
        return cartFoodsList.size
    }
    override fun onBindViewHolder(holder: CartItemHolder, position: Int) {

        var cartFood = cartFoodsList.get(position)
        val binding = holder.item

        binding.foodNameTextView.text = cartFood.food_name
        binding.foodPriceTextView.text = "₺${cartFood.food_price}"
        binding.orderAmountText.text = cartFood.food_quantity.toString()

        val url = "http://kasimadalan.pe.hu/yemekler/resimler/${cartFood.food_image_name}"
        Glide.with(mContext).load(url).into(binding.imageViewFood)

        binding.minusButton.setOnClickListener {
            if (cartFood.food_quantity > 0){
                cartFood.food_quantity--
                binding.orderAmountText.text = cartFood.food_quantity.toString()
                if (cartFood.food_quantity == 0){
                    viewModel.deleteFood(cartFood.cart_food_id,cartFood.username)
                }
            }
        }

        binding.plusButton.setOnClickListener {
            cartFoodsList.forEach {
                if(cartFood.food_name == it.food_name){
                    viewModel.deleteFood(it.cart_food_id,it.username)
                    quantity += it.food_quantity
                    viewModel.addToCart(cartFood.food_name,cartFood.food_image_name,cartFood.food_price,quantity,"elif_oksas")
                }
            }
            cartFood.food_quantity++
            binding.orderAmountText.text = cartFood.food_quantity.toString()
        }

        binding.deleteButton.setOnClickListener {
            viewModel.deleteFood(cartFood.cart_food_id,cartFood.username)
        }
    }



}