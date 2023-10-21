package com.elifoksas.foodorderingapp.data.datasource

import android.util.Log
import com.elifoksas.foodorderingapp.data.entity.CartFoods
import com.elifoksas.foodorderingapp.data.entity.Foods
import com.elifoksas.foodorderingapp.retrofit.FoodsDao
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FoodDataSource(var fdao:FoodsDao) {
    suspend fun addFavorites(foods : Foods) = withContext(Dispatchers.IO){
        var db = FirebaseFirestore.getInstance()
        val food = hashMapOf(
            "food_id" to foods.food_id,
            "food_name" to foods.food_name,
            "food_price" to foods.food_price,
            "food_image_name" to foods.food_image_name,
        )

// Add a new document with a generated ID
        db.collection("${FirebaseAuth.getInstance().currentUser?.uid}")
            .document("${foods.food_id}")
            .set(food)
            .addOnSuccessListener { documentReference ->
                Log.d("TAG", "DocumentSnapshot added with ID: ")
            }
            .addOnFailureListener { e ->
                Log.w("TAG", "Error adding document", e)
            }
        Log.d("Girdi",foods.food_image_name.toString())
    }

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
        try {
            val response = fdao.loadCart(username)
            return@withContext response.cart_foods
        }catch (e:Exception){
            Log.e("hata mesajı",e.message.toString())
            return@withContext emptyList()
        }

    }

    suspend fun deleteFood(cart_food_id : Int, username : String){
        val response = fdao.deleteFood(cart_food_id,username)
        Log.e("Delete Food","Succes: ${response.success} - Message ${response.message}")
    }
}