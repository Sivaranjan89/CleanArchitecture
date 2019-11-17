package com.droid.cleanarchitecture.home.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.droid.cleanarchitecture.R
import com.droid.cleanarchitecture.home.adapter.CategoryAdapter
import com.droid.cleanarchitecture.home.viewmodel.HomeViewModel
import com.droid.cleanarchitecture.utils.ARG_NAME
import com.droid.cleanarchitecture.utils.FURNITURE
import com.droid.cleanarchitecture.utils.LAPTOP
import com.mobeewave.retail.model.Product

class CategoryFragment() : Fragment() {

    //val model: HomeViewModel by viewModel()
    lateinit var model: HomeViewModel

    private var recyclerView: RecyclerView? = null

    private var adapter: CategoryAdapter? = null

    private var title: TextView? = null


    companion object {
        fun newInstance(name: String): CategoryFragment {
            val fragment = CategoryFragment()

            val bundle = Bundle().apply {
                putString(ARG_NAME, name)
            }

            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        model = activity?.run {
            ViewModelProviders.of(this)[HomeViewModel::class.java]
        } ?: throw Exception("Invalid Activity")
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.category_fragment, null)

        initialize(view)

        arguments?.let { bundle ->

            val name: String = bundle.getString(ARG_NAME, "")

            title?.text = name

            activity?.let { ctx ->
                if (name.equals(LAPTOP)) {
                    loadList(model.laptop.value!!)
                } else if (name.equals(FURNITURE)) {
                    loadList(model.furniture.value!!)
                }
            }
        }

        return view
    }

    private fun loadList(products: ArrayList<Product>) {
        adapter = CategoryAdapter(products, activity, model)
        recyclerView?.setAdapter(adapter)
    }

    private fun initialize(view: View) {
        title = view.findViewById(R.id.category_name)
        recyclerView = view.findViewById(R.id.category_list)
        recyclerView?.setLayoutManager(
            LinearLayoutManager(
                activity,
                RecyclerView.HORIZONTAL,
                false
            )
        )
    }


}