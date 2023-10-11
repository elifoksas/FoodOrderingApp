package com.elifoksas.foodorderingapp.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.elifoksas.foodorderingapp.data.entity.Foods
import com.elifoksas.foodorderingapp.databinding.MainpageItemBinding
import com.elifoksas.foodorderingapp.ui.viewmodel.HomePageViewModel

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

        binding.foodName.text = food.yemek_adi
        binding.foodPrice.text = food.yemek_fiyat.toString()


    }


}