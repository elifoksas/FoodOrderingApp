package com.elifoksas.foodorderingapp.data.entity

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class FoodsResponse(
    @SerializedName("yemekler")var foods:List<Foods>,
    @SerializedName("success")var success:Int) : Serializable
