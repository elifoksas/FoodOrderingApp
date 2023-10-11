package com.elifoksas.foodorderingapp.retrofit

import com.elifoksas.foodorderingapp.data.entity.FoodsResponse
import retrofit2.http.GET

interface FoodsDao {
    //http://kasimadalan.pe.hu/yemekler/tumYemekleriGetir.php

    @GET("yemekler/tumYemekleriGetir.php")
    suspend fun loadFoods() : FoodsResponse
}