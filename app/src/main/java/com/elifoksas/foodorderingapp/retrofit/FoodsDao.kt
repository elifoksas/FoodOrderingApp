package com.elifoksas.foodorderingapp.retrofit

import com.elifoksas.foodorderingapp.data.entity.CRUDResponse
import com.elifoksas.foodorderingapp.data.entity.CartFoodsResponse
import com.elifoksas.foodorderingapp.data.entity.FoodsResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface FoodsDao {

    @GET("yemekler/tumYemekleriGetir.php")
    suspend fun loadFoods() : FoodsResponse

    @POST("yemekler/sepeteYemekEkle.php")
    @FormUrlEncoded
    suspend fun addToCart(
                          @Field("yemek_adi") food_name : String,
                          @Field("yemek_resim_adi") food_image_name : String,
                          @Field("yemek_fiyat") food_price : Int,
                          @Field("yemek_siparis_adet") food_quantity : Int,
                          @Field("kullanici_adi") username : String) : CRUDResponse


    @POST("yemekler/sepettekiYemekleriGetir.php")
    @FormUrlEncoded
    suspend fun loadCart(@Field("kullanici_adi") username : String)  : CartFoodsResponse

    @POST("yemekler/sepettenYemekSil.php")
    @FormUrlEncoded
    suspend fun deleteFood(
        @Field("sepet_yemek_id") cart_food_id : Int,
        @Field("kullanici_adi") username : String)  : CRUDResponse
}