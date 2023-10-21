package com.elifoksas.foodorderingapp.data.repository

import com.elifoksas.foodorderingapp.data.datasource.FoodDataSource
import com.elifoksas.foodorderingapp.data.entity.CartFoods
import com.elifoksas.foodorderingapp.data.entity.Foods

class FoodRepository (var fds:FoodDataSource){

    suspend fun loadFoods() : List<Foods> = fds.loadFoods()
    suspend fun addFavorites(foods: Foods) = fds.addFavorites(foods)

    suspend fun addToCart(food_name : String,
                          food_image_name : String,
                          food_price : Int,
                          food_quantity : Int,
                          username : String) = fds.addToCart(food_name,food_image_name,food_price,food_quantity,username)

    suspend fun loadCart(username : String) :List<CartFoods> = fds.loadCart(username)

    suspend fun deleteFood(cart_food_id : Int, username : String) = fds.deleteFood(cart_food_id,username)

}