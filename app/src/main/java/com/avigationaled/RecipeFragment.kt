package com.avigationaled

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment

class RecipeFragment : Fragment() {
    private var recipeImageView: ImageView? = null
    private var recipeNameTextView: TextView? = null
    private var recipeIngredientsTextView: TextView? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout  for this fragment
        val view = inflater.inflate(R.layout.fragment_recipe, container, false)

        // Bind the views
        recipeImageView = view.findViewById(R.id.recipeImage)
        recipeNameTextView = view.findViewById(R.id.recipeName)
        recipeIngredientsTextView = view.findViewById(R.id.recipeIngredients)

        // Here you can set the recipe details using the  views
        // For example:
        // recipeNameTextView?.text = "Chocolate Cake"
        // recipeIngredientsTextView?.text = "Flour, Sugar, Cocoa Powder,  etc."
        // And load the image using a library like Glide or Picasso
        // Glide.with(this).load(recipeImageUrl).into(recipeImageView)

        return view
    }

    // Other methods such as onViewCreated() if needed for further initialization

}