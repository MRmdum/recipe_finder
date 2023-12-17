package com.avigationaled

import android.annotation.SuppressLint
import android.content.Context
import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.os.Debug
import android.text.method.ScrollingMovementMethod
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [page1.newInstance] factory method to
 * create an instance of this fragment.
 */
class page1 : Fragment() {
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

    @SuppressLint("SuspiciousIndentation")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

//            lifecycleScope.launch{
//                val url = "https://www.themealdb.com/api/json/v1/1/search.php?f=a"
//                HttpKtorClient().fromHttpGetWriteDB(url,requireContext())
//            }

            // Inflate the layout for this fragment
            val view = inflater.inflate(R.layout.fragment_page1, container, false)

            val sharePreference = context?.getSharedPreferences("MY_PRE", Context.MODE_PRIVATE)

            val meal_title: TextView? = view?.findViewById(R.id.tV_titre)
            val meal_ingr: TextView? = view?.findViewById(R.id.tV_ingredients)
            val meal_descr: TextView? = view?.findViewById(R.id.tV_description)
            val meal_image: ImageView? = view?.findViewById(R.id.imageView3)

            if(sharePreference!=null){
                if (meal_title != null) {
                    meal_title.text = sharePreference.getString("Meal_name","").toString()
                }
                if (meal_ingr != null) {
                    meal_ingr.text = sharePreference.getString("Ingredient","").toString()
                }

                if (meal_descr != null) {
                    meal_descr.movementMethod = ScrollingMovementMethod()
                    meal_descr.text = sharePreference.getString("Descr","").toString()
                }

                if (meal_image != null) {
                    context?.let {
                        Glide.with(it)
                            .load(sharePreference.getString("ImageUrl","").toString())
                            .placeholder(R.drawable.default_meal)
                            .transition(DrawableTransitionOptions.withCrossFade())
                            .into(meal_image)
                    }
                }

            }
            val buttonFragmentA: Button? = view?.findViewById(R.id.Button)
            buttonFragmentA?.setOnClickListener {
            findNavController().navigate(R.id.mainfrag)

//            val toast = Toast.makeText(context, text.toString(), Toast.LENGTH_LONG) // in Activity
//            toast.show()
//            Log.d("HttpRequest",text.toString())
        }
        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment page1.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            page1().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}