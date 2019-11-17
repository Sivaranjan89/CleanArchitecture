package com.droid.cleanarchitecture.home.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.droid.cleanarchitecture.R
import com.droid.cleanarchitecture.home.view.fragment.CategoryFragment
import com.droid.cleanarchitecture.home.viewmodel.HomeViewModel
import com.droid.cleanarchitecture.utils.FURNITURE
import com.droid.cleanarchitecture.utils.LAPTOP
import com.droid.cleanarchitecture.utils.inTransaction
import org.koin.android.viewmodel.ext.android.viewModel

class HomeActivity : AppCompatActivity() {

    val model: HomeViewModel by viewModel()
    //lateinit var model: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_activity)

        loadFragments()

        //model = ViewModelProviders.of(this).get(HomeViewModel::class.java)

        model.fetchProducts()
    }

    private fun loadFragments() {
        supportFragmentManager.inTransaction {
            replace(R.id.laptop_container, CategoryFragment.newInstance(LAPTOP))
            replace(R.id.furniture_container, CategoryFragment.newInstance(FURNITURE))
        }
    }
}