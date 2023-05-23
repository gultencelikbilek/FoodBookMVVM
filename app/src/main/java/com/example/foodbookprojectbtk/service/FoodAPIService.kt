package com.example.foodbookprojectbtk.service

import com.example.foodbookprojectbtk.model.Food
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class FoodAPIService {

    //https://raw.githubusercontent.com/atilsamancioglu/BTK20-JSONVeriSeti/master/besinler.json
    //burdaki BASE_URL  = https://raw.githubusercontent.com

    private val BASE_URL  = "https://raw.githubusercontent.com"
    private val api = Retrofit.Builder() //retrofir objesini oluşturmam için
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())//json formatını modele çevireceğiz
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) //rxjava kullandığım için bu yapıyı kullanıyorum
        .build()
        .create(FoodAPI::class.java)

    fun getData () : Single<List<Food>>{
        return api.getFood() //foodapi clasımızı api service bağladım

    }

}