package com.elifoksas.foodorderingapp.data.repository

import com.elifoksas.foodorderingapp.data.datasource.FoodDataSource
import com.elifoksas.foodorderingapp.data.entity.Foods

class FoodRepository (var fds:FoodDataSource){

    suspend fun loadFoods() : List<Foods> = fds.loadFoods()

}