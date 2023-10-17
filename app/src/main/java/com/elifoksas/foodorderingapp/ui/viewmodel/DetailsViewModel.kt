package com.elifoksas.foodorderingapp.ui.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.elifoksas.foodorderingapp.data.entity.CartFoods
import com.elifoksas.foodorderingapp.data.repository.FoodRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.log

@HiltViewModel
class DetailsViewModel  @Inject constructor(var frepo : FoodRepository): ViewModel(){

    var cardFoodList = MutableLiveData<List<CartFoods>>()

    init {
        loadCart("elif_oksas")
    }
    fun loadCart(username:String){
        CoroutineScope(Dispatchers.Main).launch {
            Log.d("Girdi2","girdi2")
            if (frepo.loadCart(username).isNotEmpty()){
                cardFoodList.value = frepo.loadCart(username)

            }
        }
    }
    fun deleteFood(cart_food_id : Int, username : String){
        CoroutineScope(Dispatchers.Main).launch {

            frepo.deleteFood(cart_food_id,username)
            cardFoodList.value = frepo.loadCart(username)

            loadCart(username)
        }
    }
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