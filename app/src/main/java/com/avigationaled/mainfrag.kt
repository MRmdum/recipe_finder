package com.avigationaled

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.SearchView
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [mainfrag.newInstance] factory method to
 * create an instance of this fragment.
 */
class mainfrag : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        var actualSearch :String = ""
        val list2meal:List<Meal>?=null
        lifecycleScope.launch{
            val url = "https://www.themealdb.com/api/json/v1/1/search.php?s="
            HttpKtorClient().fromHttpGetWriteDB(url,requireContext())

            val list2meal = UserRepository(requireContext()).getAllMeal()
            if (list2meal != null) {
                val recyclerView: RecyclerView? = view?.findViewById(R.id.recyclerView)

                if (recyclerView != null) {

                    recyclerView.layoutManager = LinearLayoutManager(requireContext())
                    recyclerView.adapter = CustomAdapter(list2meal,
                                                        requireContext(), this@mainfrag)
                }
            }

        }

        val view = inflater.inflate(R.layout.recipy_search, container, false)

        val spinner = view.findViewById<Spinner>(R.id.spinner)
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.optionsSpinner,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                // Handle item selection here
                val selectedOption = spinner.selectedItem.toString()
                if (selectedOption =="Recipies"){
                    updateSearchRecipicies(actualSearch)
                }else{
                    upadateSearchIngredients(actualSearch,list2meal)
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                spinner.setSelection(1)
            }
        }

        var searchView :SearchView = view.findViewById(R.id.searchV_Research)
        searchView.clearFocus()
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String): Boolean {
                actualSearch = query
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                actualSearch = newText
                val selectedOption = spinner.selectedItem.toString()
                if (selectedOption =="Recipies"){
                    updateSearchRecipicies(actualSearch)
                }else{
                    upadateSearchIngredients(actualSearch,list2meal)
                }
                return false
            }



        })

        return view
    }

    private fun updateSearchRecipicies(search:String){
        lifecycleScope.launch{
            try {
                val list2meal = UserRepository(requireContext()).getMealByName(search)
                if (list2meal != null) {
                    val recyclerView: RecyclerView? = view?.findViewById(R.id.recyclerView)
                    for(meal in list2meal){
                        Log.d("DB result",meal.toString())

                    }
                    if (recyclerView != null) {
                        recyclerView.layoutManager = LinearLayoutManager(requireContext())
                        recyclerView.adapter = CustomAdapter(list2meal,
                            requireContext(), this@mainfrag)
                    }
                }
            }catch (e:Exception){Log.d("ErrorReadSQLite",e.toString())}
        }
    }
    private fun upadateSearchIngredients(search:String, list2meal:List<Meal>?){
        if (list2meal != null) {
            var list2Ingredient: MutableList<Meal>? = mutableListOf()
            for (meal in list2meal) {
                // Create a list of ingredients for the current meal
                val ingredients = listOf(
                    meal.strIngredient1, meal.strIngredient2, meal.strIngredient3, meal.strIngredient4,
                    meal.strIngredient5, meal.strIngredient6, meal.strIngredient7, meal.strIngredient8,
                    meal.strIngredient9, meal.strIngredient10, meal.strIngredient11, meal.strIngredient12,
                    meal.strIngredient13, meal.strIngredient14, meal.strIngredient15, meal.strIngredient16,
                    meal.strIngredient17, meal.strIngredient18, meal.strIngredient19, meal.strIngredient20
                )

                // Join the list of ingredients into a single string
                val joinedIngr = ingredients.joinToString(" / ")

                // Check if the specified ingredient is present in the current meal
                val filteredIngr = ingredients.filter { it?.contains(search) ?: false }
                if (filteredIngr.isNotEmpty()) {
                    list2Ingredient?.add(meal)
                }
            }
            lifecycleScope.launch{
                try {

                    if (list2Ingredient != null) {
                        val recyclerView: RecyclerView? = view?.findViewById(R.id.recyclerView)
                        for(meal in list2Ingredient){
                            Log.d("DB result",meal.toString())

                        }
                        if (recyclerView != null) {
                            recyclerView.layoutManager = LinearLayoutManager(requireContext())
                            recyclerView.adapter = CustomAdapter(list2Ingredient,
                                requireContext(), this@mainfrag)
                        }
                    }
                }catch (e:Exception){Log.d("ErrorReadSQLite",e.toString())}
            }

        }
    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment mainfrag.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            mainfrag().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}