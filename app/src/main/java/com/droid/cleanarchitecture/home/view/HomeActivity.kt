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

/**
 * HomePage displaying list of products based on Categories
 */
class HomeActivity : AppCompatActivity() {

    /**
     * Injecting HomeViewModel
     */
    val model: HomeViewModel by viewModel()

    private var cartCounter: TextView? = null

    /**
     * Here we will initialize the views and fetch necessary data
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /**
         * Set the home_activity as the main view for this activity
         * @link home_activity
         */
        setContentView(R.layout.home_activity)

        cartCounter = findViewById(R.id.cart_count)

        model.prePopulateProducts(ProductsDatabase.getInstance(this))

        /**
         * Observe the products being fetched from database and save it to our viewmodel.
         * Load the fragments once it has been saved.
         */
        model.getProducts()?.observe(this, Observer {
            /**
             * @param it will give a List<ProductsEntity>
             */
            model.list = it
            loadFragments()
        })

        model.clickedProduct.observe(this, Observer {
            navigateToProductDetails(it)
        })
    }

    /**
     * Update Cart counter onResume
     */
    override fun onResume() {
        super.onResume()
        updateCartCounter()
    }

    /**
     * Navigates to PDP page
     */
    private fun navigateToProductDetails(product: ProductsEntity) {
        openActivity(ProductDetailActivity::class.java) {
            putLong(PRODUCT, product.productId)
        }
    }

    /**
     * Load the CategoryFragment by making use of the extension
     */
    private fun loadFragments() {
        supportFragmentManager.inTransaction {
            replace(R.id.laptop_container, CategoryFragment.newInstance(LAPTOP))
            replace(R.id.furniture_container, CategoryFragment.newInstance(FURNITURE))
        }
    }

    /**
     * Updates the Cart Counter
     */
    private fun updateCartCounter() {
        GlobalScope.launch {
            val cartItems = model.getCartProducts()
            var cartCount = 0

            if (cartItems != null) {
                for (item in cartItems) {
                    cartCount = cartCount + item.quantity
                }
            }

            /**
             * @param cartCounter should be updated in the UI thread
             */
            runOnUiThread({
                cartCounter?.text = cartCount.toString()
            })
        }
    }
}