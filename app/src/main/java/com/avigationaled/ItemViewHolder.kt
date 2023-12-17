package com.avigationaled

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ItemViewHolder(itemView: View, private val clickListener: (Int) -> Unit) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
    val textViewName: TextView = itemView.findViewById(R.id.textView2)
    val textViewingredient: TextView = itemView.findViewById(R.id.recipeIngredients)
    val imageViewMeal: ImageView = itemView.findViewById(R.id.recipeImage)

    init {
        // Set click listener for the whole item view
        itemView.setOnClickListener(this)
    }

    // Implement the onClick method
    override fun onClick(view: View) {
        // Pass the clicked item position to the clickListener
        clickListener(adapterPosition)
    }
}