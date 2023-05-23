package com.example.foodbookprojectbtk.util

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import androidx.preference.PreferenceManager

class SpecialSharedPrefernces {

    companion object{

        private val TIME = "time"
        private var sharedPrefernces : SharedPreferences? = null

        @Volatile
        private var instance : SpecialSharedPrefernces ? = null
        private val lock = Any()


        operator fun invoke(context:Context) : SpecialSharedPrefernces  = instance ?: synchronized(lock){
            instance ?: createSpecialSharedPrefernces(context).also {
                instance = it
            }
        }


        private fun createSpecialSharedPrefernces(context:Context) : SpecialSharedPrefernces {
            sharedPrefernces = PreferenceManager.getDefaultSharedPreferences(context)
            return SpecialSharedPrefernces()
        }


    }

    fun saveTime(time: Long){
        sharedPrefernces?.edit(commit = true){
            putLong(TIME,time)

        }
    }

    fun getTime() = sharedPrefernces?.getLong(TIME,0) //getlong çnkü yukarda putLong verilmiş
}