package com.elifoksas.foodorderingapp.data.datasource

import android.util.Log
import com.elifoksas.foodorderingapp.data.entity.CartFoods
import com.elifoksas.foodorderingapp.data.entity.Foods
import com.elifoksas.foodorderingapp.retrofit.FoodsDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FoodDataSource(var fdao:FoodsDao) {

    suspend fun loadFoods() : List<Foods> = withContext(Dispatchers.IO){
        return@withContext fdao.loadFoods().foods
    }

    suspend fun addToCart(food_name : String,
                          food_image_name : String,
                          food_price : Int,
                          food_quantity : Int,
                          username : String){
        val response = fdao.addToCart(food_name,food_image_name,food_price,food_quantity,username)
        Log.e("Add To Cart","Succes: ${response.success} - Message ${response.message}")
    }

    suspend fun loadCart(username : String) :List<CartFoods> = withContext(Dispatchers.IO){
        val response = fdao.loadCart(username)
        return@withContext response.cart_foods
    }

    suspend fun deleteFood(cart_food_id : Int, username : String){
        val response = fdao.deleteFood(cart_food_id,username)
        Log.e("Delete Food","Succes: ${response.success} - Message ${response.message}")
    }
}