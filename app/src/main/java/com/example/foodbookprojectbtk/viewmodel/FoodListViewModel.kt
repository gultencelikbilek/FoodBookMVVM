package com.example.foodbookprojectbtk.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.foodbookprojectbtk.model.Food
import com.example.foodbookprojectbtk.service.FoodAPIService
import com.example.foodbookprojectbtk.service.FoodDatabase
import com.example.foodbookprojectbtk.util.SpecialSharedPrefernces
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch

class FoodListViewModel(application: Application): BaseWiewModel(application) {
    val foodList = MutableLiveData<List<Food>>() //mutablelivedata değiştirilebilir demek
    val foodErrorMessage = MutableLiveData<Boolean>()
    val foodLoading = MutableLiveData<Boolean>()
    private var updateTime = 0.2 * 60 * 1000 * 1000 * 1000L //dakikanın nanotime çevrilmesi


    private val foodAPIService = FoodAPIService()
    private val disposable = CompositeDisposable() //birden fazla istek atılırsa bir fragmentta disposbla yapısı ile ne zaman istersek bu isteği durdurabilir
    //disposable = kullan-at
    //herbir istek disposable olabilir
    private val specialSharedPrefernces = SpecialSharedPrefernces(getApplication())

    fun refreshData(){
        val saveTime = specialSharedPrefernces.getTime()

        if (saveTime != null && saveTime != 0L && System.nanoTime() - saveTime  < updateTime ){
            //SqLiteden verileri çek
            getDataSQLite()
        }else{
            //verileri internetten al
            getDataInternet()

        }

    }

    fun refreshFromInternet(){
        getDataInternet()
    }

    private fun getDataSQLite(){
        foodLoading.value = true
        launch {
            //dao içinde suspend fun olduğu için launch kullanıldı
         val foodList =    FoodDatabase(getApplication()).foodDao().getAllFood()
         foodShow(foodList)
            Toast.makeText(getApplication(),"Besinleri Roomdan aldık",Toast.LENGTH_SHORT).show()
        }

    }




    private fun getDataInternet(){
        foodLoading.value = true
        disposable.add(
            foodAPIService.getData()
                .subscribeOn(Schedulers.newThread())//yeni thread oluşturuyoruz ve oraya atacak
                .observeOn(AndroidSchedulers.mainThread()) //ana thread kullanıcıya göstereceğiz yerde
                .subscribeWith(object : DisposableSingleObserver<List<Food>>(){
                    override fun onSuccess(t: List<Food>) {
                      //  foodList.value = t
                      //  foodErrorMessage.value = false
                      //  foodLoading.value = false
                        sqLiteHide(t)
                        Toast.makeText(getApplication(),"Verileri internetten aldık",Toast.LENGTH_SHORT).show()

                    }

                    override fun onError(e: Throwable) {
                       foodErrorMessage.value = true
                        foodLoading.value = false
                        e.printStackTrace()
                    }
                })
        )

    }

    private fun foodShow(foodsList: List<Food>){
        foodList.value = foodsList
        foodErrorMessage.value = false
        foodLoading.value = false
    }

    private fun sqLiteHide(foodsList : List<Food>){
        //suspend fun ları direk çağıramam ama coroutinelr ile çağıraiblirim.
        launch { //threadleri bloklamaz iptal edilirse bildirilir
            //suspend fonk ile ilgili her şeyi yapabiliirm

            val dao = FoodDatabase(getApplication()).foodDao()
            dao.deleteAllFood()
            val uuidList = dao.insertAll(*foodsList.toTypedArray()) //brsinleri tek tek verecektir * ile
             var i = 0
            while (i < foodsList.size){
                foodsList[i].uuid = uuidList[i].toInt()
                i = i+1
            }
            foodShow(foodsList)

        }
        specialSharedPrefernces.saveTime(System.nanoTime())



    }


}