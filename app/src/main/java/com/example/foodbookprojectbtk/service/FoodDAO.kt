package com.example.foodbookprojectbtk.service

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.foodbookprojectbtk.model.Food

@Dao
interface FoodDAO {

    @Insert()
    suspend fun insertAll(vararg food : Food) : List<Long>//vararg ile birden fazla ve istediğmiz sayıda besin ekler
    //list<Long> long id'ler için

    @Query("SELECT * FROM food")
    suspend fun getAllFood() : List<Food>

    //tek bir veri çekmek için
    @Query("SELECT * FROM food WHERE uuid = :foodId")
    suspend fun getFood(foodId : Int) : Food

    @Query("DELETE FROM food")
    suspend fun  deleteAllFood()

}