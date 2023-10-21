package com.elifoksas.foodorderingapp.ui.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.elifoksas.foodorderingapp.data.entity.Foods
import com.elifoksas.foodorderingapp.data.repository.FoodRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class HomePageViewModel @Inject constructor(var frepo : FoodRepository): ViewModel(){

    var foodsList = MutableLiveData<List<Foods>?>()

    var favoriteControl = MutableLiveData<Boolean>()

    init {
        loadFoods()
    }
    fun addFavorites(favoriteFoods: Foods){
        CoroutineScope(Dispatchers.Main).launch {
            val success = frepo.addFavorites(favoriteFoods)

            favoriteControl.value = success == 1
        }
    }
    fun loadFoods(){
        CoroutineScope(Dispatchers.Main).launch {
            try {
                foodsList.value =frepo.loadFoods()
                frepo.loadFoods().forEach {
                    Log.d("foods",it.food_name)
                }
            }catch (e: Exception){
                 Log.e("foods",e.toString())
            }

        }
    }

    fun filterFoods(query: String){
        val filteredList = foodsList.value?.filter { food ->
            food.food_name.toLowerCase(Locale.getDefault()).contains(query.toLowerCase(Locale.getDefault()))
        }
        foodsList.value = filteredList
    }

}


