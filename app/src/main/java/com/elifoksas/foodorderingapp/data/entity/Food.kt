package com.elifoksas.foodorderingapp.data.entity

import java.io.Serializable

data class Food(
    var food_id : Int,
    var food_name : String,
    var food_image_name : String,
    var food_price : Int ) : Serializable {}
