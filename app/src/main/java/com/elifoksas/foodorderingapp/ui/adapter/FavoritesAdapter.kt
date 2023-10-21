package com.elifoksas.foodorderingapp.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.elifoksas.foodorderingapp.data.entity.Foods
import com.elifoksas.foodorderingapp.databinding.MainpageItemBinding
import com.elifoksas.foodorderingapp.ui.viewmodel.FavoritesViewModel

class FavoritesAdapter(var mContext: Context, var foodsList:List<Foods>, var viewModel: FavoritesViewModel)
    : RecyclerView.Adapter<FavoritesAdapter.FavoritesItemHolder>() {

    inner class FavoritesItemHolder(var item:MainpageItemBinding) : RecyclerView.ViewHolder(item.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritesItemHolder {
        val binding = MainpageItemBinding.inflate(LayoutInflater.from(mContext), parent,false)
        return FavoritesItemHolder(binding)
    }

    override fun getItemCount(): Int {
        return foodsList.size
    }

    override fun onBindViewHolder(holder: FavoritesItemHolder, position: Int) {
        val food = foodsList.get(position)
        val binding = holder.item

        binding.foodName.text = food.food_name
        binding.foodPrice.text = "₺${food.food_price}"

        val url = "http://kasimadalan.pe.hu/yemekler/resimler/${food.food_image_name}"
        Glide.with(mContext).load(url).into(binding.foodImage)



    }
}