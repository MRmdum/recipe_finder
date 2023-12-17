package com.avigationaled

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
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

        lifecycleScope.launch{
            val url = "https://www.themealdb.com/api/json/v1/1/search.php?s=Arrabiata"
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

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.recipy_search, container, false)



        val text2recherche: TextView? = view?.findViewById(R.id.txtV_Research)
        text2recherche?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
            override fun afterTextChanged(editable: Editable?) {
                // This method is called to notify you that the characters within `editable` have changed.
                val text = editable.toString()
                // Do something with the new text
                lifecycleScope.launch{
                    try {
                        val list2meal = UserRepository(requireContext()).getMealByName(text)
                        if (list2meal != null) {
                            for(meal in list2meal){
                                Log.d("DB result",meal.toString())
                            }
                        }
                    }catch (e:Exception){Log.d("ErrorReadSQLite",e.toString())}
                }
            }
        })

        val buttonFragmentA: ImageButton? = view?.findViewById(R.id.Button)
        buttonFragmentA?.setOnClickListener {
            findNavController().navigate(R.id.page1)
        }



        return view
    }

    //findViewById<Button>(R.id.supabutton).setOnClickListener {
    //    Log.d("BUTTONS", "User tapped the Supabutton")
    //}

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