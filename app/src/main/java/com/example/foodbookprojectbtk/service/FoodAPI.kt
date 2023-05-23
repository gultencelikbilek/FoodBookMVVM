package com.example.foodbookprojectbtk.service

import com.example.foodbookprojectbtk.model.Food
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET

interface FoodAPI {
    //internet sitesindeki istekler @GET(vermek) : Genelde veri çekilcekse apiden veri alıp sunucumuza veri verecekseek @GET
    //@POST(yollamak) : Sunucuya veri yollanacaksa @POST kullanılır

    //BASE_URL  = Ana url hangisi ise o yazılır
    //https://raw.githubusercontent.com/atilsamancioglu/BTK20-JSONVeriSeti/master/besinler.json
    //burdaki BASE_URL  = https://raw.githubusercontent.com
    @GET(value = "atilsamancioglu/BTK20-JSONVeriSeti/master/besinler.json")
    fun getFood() : Single<List<Food>> //RxJava Single : Tek bir defa veri çekicek

}