package com.example.foodbookprojectbtk.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Food(
    @ColumnInfo("isim")
    @SerializedName("isim")//json dosysındaki isim verilmeli birebir aynı olması gerekir
    val foodName : String?,

    @ColumnInfo("kalori")
    @SerializedName("kalori")
    val foodCalori : String?,

    @ColumnInfo("karbonhidrat")
    @SerializedName("karbonhidrat")
    val foodCarbonhdrt : String?,

    @ColumnInfo("protein")
    @SerializedName("protein")
    val foodProtein : String?,

    @ColumnInfo("yag")
    @SerializedName("yag")
    val foodOil : String?,

    @ColumnInfo("gorsel")
    @SerializedName("gorsel")
    val foodImage : String?
) {
    @PrimaryKey(autoGenerate = true)
    var uuid : Int = 0

}