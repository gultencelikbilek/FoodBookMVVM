package com.example.foodbookprojectbtk.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.foodbookprojectbtk.R
import com.example.foodbookprojectbtk.databinding.FoodRecyclerRowBinding
import com.example.foodbookprojectbtk.model.Food
import com.example.foodbookprojectbtk.util.doPlaceHolder
import com.example.foodbookprojectbtk.util.imageDownload
import com.example.foodbookprojectbtk.view.FoodListFragmentDirections
import kotlinx.android.synthetic.main.food_recycler_row.view.*

class FoodAdapater(val foodList : ArrayList<Food>): RecyclerView.Adapter<FoodAdapater.FoodViewHolder>(),FoodClickListener {

    class FoodViewHolder(var view : FoodRecyclerRowBinding):RecyclerView.ViewHolder(view.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        val inflater = LayoutInflater.from(parent.context)
      //  val view = inflater.inflate(R.layout.food_recycler_row,parent,false)
       //DataBindingUtil bunun içinde databinding yapıları bulunur
        val view = DataBindingUtil.inflate<FoodRecyclerRowBinding>(inflater,R.layout.food_recycler_row,parent,false)
        return FoodViewHolder(view)
    }

    override fun getItemCount(): Int {
       return foodList.size
    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
     holder.view.food =foodList[position]
        holder.view.listener = this



    /*  holder.itemView.tvFoodNameRow.text = foodList.get(position).foodName
        holder.itemView.tvFoodCaloriRow.text = foodList.get(position).foodCalori

        // görsel kısmı eklenecek

        holder.itemView.setOnClickListener {
            val action = FoodListFragmentDirections.actionFoodListFragmentToFoodDetailFragment(foodList.get(position).uuid)
            Navigation.findNavController(it).navigate(action)
        }

        holder.itemView.ivPic.imageDownload(foodList.get(position).foodImage, doPlaceHolder(holder.itemView.context))
*/
    }

    fun foodListUpdate(newFoodList : List<Food>){
        foodList.clear() //listeyi temizle
        foodList.addAll(newFoodList) //yeni listeye ekle
        notifyDataSetChanged()

    }

    override fun foodClicked(view: View) {
        val uuid = view.fooduuid.text.toString().toIntOrNull()
        uuid?.let {
            val action = FoodListFragmentDirections.actionFoodListFragmentToFoodDetailFragment(it)
            Navigation.findNavController(view).navigate(action)
        }

    }

}