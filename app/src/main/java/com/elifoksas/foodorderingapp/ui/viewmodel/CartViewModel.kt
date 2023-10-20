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
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(var frepo : FoodRepository): ViewModel() {

    var cartFoodList = MutableLiveData<List<CartFoods>?>()

    var totalPrice = MutableLiveData<Int>()

    init {
        loadCart("elif_oksas")
    }
    fun loadCart(username:String){
        CoroutineScope(Dispatchers.Main).launch {
            try {
                cartFoodList.value = frepo.loadCart(username)
            }catch (e:Exception){
                cartFoodList.value = null
                Log.e("hata mesajı",e.message.toString())
            }
            frepo.loadCart(username).forEach{
                Log.d("sipariş",it.username)
            }

            calculateTotalPrice()
        }
    }

    fun deleteFood(cart_food_id : Int, username : String){
        CoroutineScope(Dispatchers.Main).launch {
            frepo.deleteFood(cart_food_id,username)
            cartFoodList.value = frepo.loadCart(username)

            loadCart(username)
            calculateTotalPrice()
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

        calculateTotalPrice()

    }

    private fun calculateTotalPrice(){
        val foodList = cartFoodList.value
        var total = 0

        foodList?.forEach { cartFood ->
            val foodPrice = cartFood.food_price
            val quantity = cartFood.food_quantity
            total += foodPrice * quantity
        }

        totalPrice.value = total
    }

}