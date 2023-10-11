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
import javax.inject.Inject

@HiltViewModel
class HomePageViewModel @Inject constructor(var frepo : FoodRepository): ViewModel(){

    var foodsList = MutableLiveData<List<Foods>>()

    init {
        loadFoods()
    }


    fun loadFoods(){
        CoroutineScope(Dispatchers.Main).launch {
            try {
                Log.e("foods","gelio")
                foodsList.value =frepo.loadFoods()
                frepo.loadFoods().forEach {
                    Log.d("foods",it.yemek_adi)
                }
            }catch (e: Exception){
                 Log.e("foods",e.toString())
            }

        }
    }
}