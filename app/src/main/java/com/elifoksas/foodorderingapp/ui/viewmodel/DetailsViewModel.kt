package com.elifoksas.foodorderingapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.elifoksas.foodorderingapp.data.repository.FoodRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel  @Inject constructor(var frepo : FoodRepository): ViewModel(){
    fun addToCart(food_name : String,
                  food_image_name : String,
                  food_price : Int,
                  food_quantity : Int,
                  username : String){
        CoroutineScope(Dispatchers.Main).launch {
            frepo.addToCart(food_name,food_image_name,food_price,food_quantity,username)
        }
    }
}