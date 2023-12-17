package com.avigationaled

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

class CustomAdapter(private val itemList: List<Meal>, private val context: Context, private val fragment: Fragment) : RecyclerView.Adapter<ItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fiche_meal, parent, false)
        return ItemViewHolder(view, onItemClick)
    }
    val onItemClick: (Int) -> Unit = { position ->
        Log.d("TEST_CLICK","CLick")

        val sharePreference = context.getSharedPreferences("MY_PRE", Context.MODE_PRIVATE)
        val editor = sharePreference.edit()
        editor.putString("Meal","")
        editor.putString("Ingredient","")
        editor.putString("ImageUrl","")
        editor.apply()
        if (fragment != null ){
            Log.d("TEST_CLICK","PASS2E")
            findNavController(fragment).navigate(R.id.page1)
        }
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val currentItem = itemList[position]
        holder.textViewName.text = currentItem.strMeal

        val ingeredients = listOf(currentItem.strIngredient1,currentItem.strIngredient2,currentItem.strIngredient3,currentItem.strIngredient4,currentItem.strIngredient5,currentItem.strIngredient6,currentItem.strIngredient7,currentItem.strIngredient8,currentItem.strIngredient9,currentItem.strIngredient10,
            currentItem.strIngredient11,currentItem.strIngredient12,currentItem.strIngredient13,currentItem.strIngredient14,currentItem.strIngredient15,currentItem.strIngredient16,currentItem.strIngredient17,currentItem.strIngredient18,currentItem.strIngredient19,currentItem.strIngredient20)
        val joinedIngr = ingeredients.joinToString(" / ")
        holder.textViewingredient.text = joinedIngr

        Glide.with(context)
            .load(currentItem.strMealThumb)
            .placeholder(R.drawable.default_meal)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(holder.imageViewMeal)
        // Bind other data to views here



    }

    override fun getItemCount(): Int {
        return itemList.size
    }
}