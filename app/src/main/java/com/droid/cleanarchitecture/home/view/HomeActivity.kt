package com.droid.cleanarchitecture.home.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.droid.cleanarchitecture.R
import com.droid.cleanarchitecture.db.ProductsDatabase
import com.droid.cleanarchitecture.db.entity.ProductsEntity
import com.droid.cleanarchitecture.home.view.fragment.CategoryFragment
import com.droid.cleanarchitecture.home.viewmodel.HomeViewModel
import com.droid.cleanarchitecture.pdp.view.ProductDetailActivity
import com.droid.cleanarchitecture.utils.*
import org.koin.android.viewmodel.ext.android.viewModel

class HomeActivity : AppCompatActivity() {

    val model: HomeViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_activity)

        model.prePopulateProducts(ProductsDatabase.getInstance(this))

        model.getProducts()?.observe(this, Observer {
            model.list = it
            loadFragments()
        })

        model.clickedProduct.observe(this, Observer {
            navigateToProductDetails(it)
        })
    }

    private fun navigateToProductDetails(product: ProductsEntity) {
        openActivity(ProductDetailActivity::class.java) {
            putLong(PRODUCT, product.productId)
        }
    }

    private fun loadFragments() {
        supportFragmentManager.inTransaction {
            replace(R.id.laptop_container, CategoryFragment.newInstance(LAPTOP))
            replace(R.id.furniture_container, CategoryFragment.newInstance(FURNITURE))
        }
    }
}