package com.example.foodbookprojectbtk.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

open class BaseWiewModel(application: Application)  : AndroidViewModel(application),CoroutineScope{

    private val job = Job()
     override val coroutineContext: CoroutineContext
         //initialze etmek için get methodu çağırıldı
     get() = job+Dispatchers.Main //main : Arrka plandaki işler yapıldıktan sonra maine dönülecek

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }

}