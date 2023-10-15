package com.elifoksas.foodorderingapp.data.entity

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class CartFoodsResponse(
    @SerializedName("sepet_yemekler") var cart_foods : List<CartFoods>,
    @SerializedName("success") var success : Int) : Serializable
