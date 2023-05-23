package com.example.foodbookprojectbtk.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.foodbookprojectbtk.R
import com.example.foodbookprojectbtk.databinding.FragmentFoodDetailBinding
import com.example.foodbookprojectbtk.util.doPlaceHolder
import com.example.foodbookprojectbtk.util.imageDownload
import com.example.foodbookprojectbtk.viewmodel.FoodDetalViewModel
import kotlinx.android.synthetic.main.fragment_food_detail.*

@Suppress("UNREACHABLE_CODE")
class FoodDetailFragment : Fragment() {

    private lateinit var viewModel : FoodDetalViewModel
    private var foodId =0
    private lateinit var dataBinding : FragmentFoodDetailBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        dataBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_food_detail,container,false)
        return dataBinding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            foodId = FoodDetailFragmentArgs.fromBundle(it).foodId
        }

        viewModel = ViewModelProviders.of(this).get(FoodDetalViewModel::class.java)
        viewModel.getRoomData(foodId)



        observeLiveData()

    }

    fun observeLiveData(){
        viewModel.foodLiveData.observe(viewLifecycleOwner, Observer {food ->
        food?.let {
            dataBinding.choseFood = it





          /*  tvFoodName.text = it.foodName
            tvFoodCalori.text = it.foodCalori
            tvFoodCarbnhdrt.text = it.foodCarbonhdrt
            tvFoodProtein.text = it.foodProtein
            tvFoodOil.text = it.foodOil
            context?.let{
                ivFoodImage.imageDownload(food.foodImage, doPlaceHolder(it))
            }
*/
        }

        })
    }

}