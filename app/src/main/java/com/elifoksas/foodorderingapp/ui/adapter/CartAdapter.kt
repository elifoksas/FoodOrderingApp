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

    override fun getItemCount(): Int {
        return cartFoodsList.size
    }

    override fun onBindViewHolder(holder: CartItemHolder, position: Int) {
        val cartFood = cartFoodsList.get(position)
        val binding = holder.item

        binding.foodNameTextView.text = cartFood.food_name
        binding.foodPriceTextView.text = "₺${cartFood.food_price}"

        val url = "http://kasimadalan.pe.hu/yemekler/resimler/${cartFood.food_image_name}"
        Glide.with(mContext).load(url).into(binding.imageViewFood)


    }


}