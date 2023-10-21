package com.elifoksas.foodorderingapp.ui.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.elifoksas.foodorderingapp.R
import com.elifoksas.foodorderingapp.data.entity.Foods
import com.elifoksas.foodorderingapp.databinding.MainpageItemBinding
import com.elifoksas.foodorderingapp.ui.fragment.HomePageFragmentDirections
import com.elifoksas.foodorderingapp.ui.viewmodel.HomePageViewModel
import com.elifoksas.foodorderingapp.utils.gecis

class FoodsAdapter(var mContext:Context, var foodsList:List<Foods>, var viewModel:HomePageViewModel)
    :RecyclerView.Adapter<FoodsAdapter.MainPageItemHolder>(){

    inner class MainPageItemHolder(var item:MainpageItemBinding) : RecyclerView.ViewHolder(item.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainPageItemHolder {
        val binding = MainpageItemBinding.inflate(LayoutInflater.from(mContext), parent, false)
        return MainPageItemHolder(binding)
    }

    override fun getItemCount(): Int {
        return foodsList.size
    }

    override fun onBindViewHolder(holder: MainPageItemHolder, position: Int) {
        val food = foodsList.get(position)
        val binding = holder.item
        val view = holder.itemView

        binding.foodName.text = food.food_name
        binding.foodPrice.text = "₺${food.food_price}"

        val url = "http://kasimadalan.pe.hu/yemekler/resimler/${food.food_image_name}"
        Glide.with(mContext).load(url).into(binding.foodImage)

        binding.foodItemCardView.setOnClickListener {
            val transition = HomePageFragmentDirections.actionHomePageFragmentToDetailsFragment(food)
            Navigation.gecis(it,transition)
        }

        //adding food to favorites
        var isFavorite = false
        binding.favButton.setOnClickListener {
            isFavorite = !isFavorite
            if (isFavorite) {
                viewModel.addFavorites(food)
                binding.favButton.setImageResource(R.drawable.favorite_fill_icon)
            } else {
                binding.favButton.setImageResource(R.drawable.favorite_icon)
            }
        }




    }


}