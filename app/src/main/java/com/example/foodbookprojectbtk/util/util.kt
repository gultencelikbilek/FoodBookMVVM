package com.example.foodbookprojectbtk.util

import android.content.Context
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.foodbookprojectbtk.R

//eklentiler

fun ImageView.imageDownload(url : String?,placeholder : CircularProgressDrawable){
    val options = RequestOptions().placeholder(placeholder).error(R.mipmap.ic_launcher_round) //resimler yüklenmezse otomatik olaarak bunu yükler ve gössterir

    Glide.with(context)
        .setDefaultRequestOptions(options)
        .load(url)
        .into(this)

}
fun doPlaceHolder(context : Context) : CircularProgressDrawable{
    return CircularProgressDrawable(context).apply {//bir işi yaparken başka bir işide birlikte yapmaya olanak sağlar
        strokeWidth =  8f //yuvarlak dönen görünümü kalınlaştırır
        centerRadius = 40f //genişiliği
        start()

    }
}

@BindingAdapter("android:downloadImage") //xml içinde bu fonk kullanabilirim artık
fun downloadImage(view : ImageView, url : String?){
    view.imageDownload(url, doPlaceHolder(view.context))

}