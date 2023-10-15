package com.elifoksas.foodorderingapp.ui.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.elifoksas.foodorderingapp.data.entity.CartFoods
import com.elifoksas.foodorderingapp.data.entity.Foods
import com.elifoksas.foodorderingapp.data.repository.FoodRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.http.Field
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(var frepo : FoodRepository): ViewModel() {

    var cardFoodList = MutableLiveData<List<CartFoods>>()

    init {
        loadCart("elif_oksas")
    }


    fun loadCart(username:String){
        CoroutineScope(Dispatchers.Main).launch {
            cardFoodList.value = frepo.loadCart(username)

            frepo.loadCart(username).forEach{
                Log.d("sipariş",it.username)
            }
        }
    }

    suspend fun deleteFood(cart_food_id : Int, username : String){
        CoroutineScope(Dispatchers.Main).launch {
            frepo.deleteFood(cart_food_id,username)
            loadCart(username)
        }
    }

}