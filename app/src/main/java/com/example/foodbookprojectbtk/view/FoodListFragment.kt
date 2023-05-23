package com.example.foodbookprojectbtk.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodbookprojectbtk.R
import com.example.foodbookprojectbtk.adapter.FoodAdapater
import com.example.foodbookprojectbtk.viewmodel.FoodListViewModel
import kotlinx.android.synthetic.main.fragment_food_list.*


@Suppress("UNREACHABLE_CODE")
class FoodListFragment : Fragment() {

    private lateinit var viewModel : FoodListViewModel
    private val recyclerFoodAdapter = FoodAdapater(arrayListOf())



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_food_list, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //FoodListViewModel bu sınıfın bu fragmnta viewModel olarak kullanıcığını söyleirm bu yapı ile
        viewModel = ViewModelProviders.of(this).get(FoodListViewModel::class.java)
        viewModel.refreshData()

        rvFoodList.layoutManager = LinearLayoutManager(context)
        rvFoodList.adapter = recyclerFoodAdapter

        swiperefreshLayout.setOnRefreshListener {
            //kullanıcı refresh ettiğinde
            tvFoodErrorMessage.visibility = View.GONE
            rvFoodList.visibility = View.GONE
            pbFoodLoading.visibility = View.VISIBLE
           // viewModel.refreshData()
            viewModel.refreshFromInternet()//refresh edildiği zaman internetten veri çekilir
            swiperefreshLayout.isRefreshing = false
        }
        observeLiveData()


    }

    //gözlemleyeceğmiz durumlara göre neler yapılması gerekir
    fun observeLiveData(){
        viewModel.foodList.observe(viewLifecycleOwner, Observer {foodList -> //gözlemlediğim(observe) veri bana bir liste verceektir
            foodList?.let {
                rvFoodList.visibility = View.VISIBLE

                //bana verilen listeyi fonksiyonda kullanacağım
                recyclerFoodAdapter.foodListUpdate(foodList)
            }

        })

        //it : Boolean görünmesinin sebebi mutableLiveData tipini boolean vermem (viewmodelinde)
        viewModel.foodErrorMessage.observe(viewLifecycleOwner, Observer {error ->
            error?.let {
                if (it){ //if(it == true ) demek
                    tvFoodErrorMessage.visibility = View.VISIBLE
                }else{
                    tvFoodErrorMessage.visibility = View.GONE
                }

            }

        })

        viewModel.foodLoading.observe(viewLifecycleOwner, Observer {loading ->
        loading?.let {
            if (it){
                rvFoodList.visibility = View.GONE
                tvFoodErrorMessage.visibility = View.GONE
                pbFoodLoading.visibility = View.VISIBLE
            }else{
                pbFoodLoading.visibility = View.GONE
            }
        }

        })

    }
}