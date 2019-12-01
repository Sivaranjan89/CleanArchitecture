package com.droid.cleanarchitecture.home.view

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.droid.cleanarchitecture.R
import com.droid.cleanarchitecture.db.ProductsDatabase
import com.droid.cleanarchitecture.db.entity.ProductsEntity
import com.droid.cleanarchitecture.home.view.fragment.CategoryFragment
import com.droid.cleanarchitecture.home.viewmodel.HomeViewModel
import com.droid.cleanarchitecture.pdp.view.ProductDetailActivity
import com.droid.cleanarchitecture.utils.*
import com.droid.cleanarchitecture.utils.extensions.inTransaction
import com.droid.cleanarchitecture.utils.extensions.openActivity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.android.viewmodel.ext.android.viewModel

class HomeActivity : AppCompatActivity() {

    val model: HomeViewModel by viewModel()

    private var cartCounter: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_activity)

        cartCounter = findViewById(R.id.cart_count)

        model.prePopulateProducts(ProductsDatabase.getInstance(this))

        model.getProducts()?.observe(this, Observer {
            model.list = it
            loadFragments()
        })

        model.clickedProduct.observe(this, Observer {
            navigateToProductDetails(it)
        })
    }

    override fun onResume() {
        super.onResume()
        updateCartCounter()
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

    private fun updateCartCounter() {
        GlobalScope.launch {
            val cartItems = model.getCartProducts()
            var cartCount = 0

            if (cartItems != null) {
                for (item in cartItems) {
                    cartCount = cartCount + item.quantity
                }
            }

            runOnUiThread({
                cartCounter?.text = cartCount.toString()
            })
        }
    }
}