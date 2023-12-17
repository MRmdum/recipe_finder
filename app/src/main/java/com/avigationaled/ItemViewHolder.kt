package com.avigationaled

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val textViewName: TextView = itemView.findViewById(R.id.textView2)
    val textViewingredient: TextView = itemView.findViewById(R.id.recipeIngredients)
    val imageViewMeal : ImageView = itemView.findViewById(R.id.recipeImage)
// Reference other views here
}