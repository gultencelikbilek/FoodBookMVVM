package com.example.foodbookprojectbtk.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.foodbookprojectbtk.model.Food
import com.example.foodbookprojectbtk.service.FoodDatabase
import kotlinx.coroutines.launch

class FoodDetalViewModel(application: Application): BaseWiewModel(application) {

    //detayda tek bir besin geleceği için liste oluşturulmadı
    val foodLiveData = MutableLiveData<Food>()


    fun getRoomData(uuid : Int){
        launch {
            val dao = FoodDatabase(getApplication()).foodDao()
            val food = dao.getFood(uuid)
            foodLiveData.value = food
        }


    }
}