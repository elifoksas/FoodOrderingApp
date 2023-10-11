package com.elifoksas.foodorderingapp.data.datasource

import com.elifoksas.foodorderingapp.data.entity.Foods
import com.elifoksas.foodorderingapp.retrofit.FoodsDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FoodDataSource(var fdao:FoodsDao) {

    suspend fun loadFoods() : List<Foods> = withContext(Dispatchers.IO){
        return@withContext fdao.loadFoods().foods
    }
}