package com.elifoksas.foodorderingapp.data.repository

import com.elifoksas.foodorderingapp.data.datasource.FavoritesDataSource
import com.elifoksas.foodorderingapp.data.datasource.FoodDataSource
import com.elifoksas.foodorderingapp.data.entity.Foods
import java.lang.invoke.CallSite

class FavoritesRepository(var fds:FavoritesDataSource) {
    suspend fun getFavorites() = fds.getFavorites()
    suspend fun getFavoritesList()  : List<Foods> = fds.getFavoritesList()


}