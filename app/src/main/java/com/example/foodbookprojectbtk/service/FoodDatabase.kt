package com.example.foodbookprojectbtk.service

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.foodbookprojectbtk.model.Food

@Database(entities = [Food::class], version = 1)
abstract class FoodDatabase : RoomDatabase(){

    abstract fun foodDao(): FoodDAO


    //Singleton
    companion object{
        @Volatile //bu instance diğer threadlere görünür yapar
        private var instance : FoodDatabase? = null
        private val lock = Any()

        //senkronize bir şekilde eğer daha önce bir instance oluşturuldu ise onu döndür oluşturulmadı ise işlemleri senkronize yap
        operator fun invoke(context : Context) = instance ?: //?:ilr oluşturulup oluşturulmaıdğını kontrol eder
        synchronized(lock){
            instance ?: createDatabase(context).also{
                //also bu görebi yap üstüne bide başka görevi de yap demek
                instance = it
                //instance ?: ile companion object içinde bir instance nesnesi olup olmadığı kontrol ettiriyor

            }
        }

        private fun createDatabase(context : Context) =
            Room.databaseBuilder(
                context.applicationContext,
                FoodDatabase::class.java,
                "fooddatabase"
            ).build()
    }





}