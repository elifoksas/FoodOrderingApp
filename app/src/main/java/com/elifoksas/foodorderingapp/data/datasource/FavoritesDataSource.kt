package com.elifoksas.foodorderingapp.data.datasource

import android.util.Log
import com.elifoksas.foodorderingapp.data.entity.Foods
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FavoritesDataSource {
    companion object{
        var list : ArrayList<Foods> = arrayListOf()
    }

    suspend fun getFavorites() = withContext(Dispatchers.IO){
        list.clear()
        val docRef = FirebaseFirestore.getInstance()
            .collection(FirebaseAuth.getInstance().currentUser?.uid.toString())


        docRef.get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    Log.d("listdoc", "Girdi")
                    for (document in document) {
                        // Her bir belgeyi işlemek için burada döngü kullanabilirsiniz
                        val data = document.data
                        // data içindeki verileri istediğiniz şekilde işleyebilirsiniz
                        if (data!= null) {
                            val foods = Foods( food_id = data["food_id"].toString().toInt(),
                                food_name = data["food_name"].toString(),
                                food_image_name = data["food_image_name"].toString(),
                                food_price = data["food_price"].toString().toInt(),

                                // Diğer alanları burada ekle
                            )

                            list.add(foods)

                            // Şimdi Foods nesnelerini kullanabilirsiniz
                            list.forEach { food ->
                                // food ile yapmak istediğiniz işlemleri burada gerçekleştirin
                                Log.d("listdoc", food.food_id.toString())

                            }
                        }
                    }


                } else {
                    Log.d("listdoc", "Girmedi")
                }
            }
            .addOnFailureListener { exception ->
                Log.d("listdoc", exception.message.toString())
            }
    }
    suspend fun getFavoritesList() :List<Foods> = withContext(Dispatchers.IO){
        return@withContext list
    }


}