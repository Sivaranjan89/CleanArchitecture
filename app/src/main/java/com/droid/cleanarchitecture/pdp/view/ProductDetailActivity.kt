package com.droid.cleanarchitecture.pdp.view

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.droid.cleanarchitecture.R
import com.droid.cleanarchitecture.cart.view.CartActivity
import com.droid.cleanarchitecture.databinding.PdpActivityBinding
import com.droid.cleanarchitecture.db.entity.ProductsEntity
import com.droid.cleanarchitecture.pdp.viewmodel.PDPViewModel
import com.droid.cleanarchitecture.utils.PRODUCT
import com.droid.cleanarchitecture.utils.extensions.getCartProduct
import com.droid.cleanarchitecture.utils.extensions.openActivity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject

class ProductDetailActivity : AppCompatActivity(), KoinComponent {

    private val model: PDPViewModel by inject()

    private var productId: Long? = 0
    private var product: ProductsEntity? = null

    private var back: ImageView? = null
    private var addToCart: Button? = null
    private var cartCounter: TextView? = null

    private lateinit var binding: PdpActivityBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        productId = intent.getLongExtra(PRODUCT, 0)

        productId?.let {
            model.getProduct(it)?.observe(this, Observer {
                product = it

                binding = DataBindingUtil.setContentView(this, R.layout.pdp_activity)
                binding.product = product
                binding.model = model

                init()
            })
        }

        model.addToCart.observe(this, Observer { added ->
            if (!added) {
                addToCart?.text = getString(R.string.add_to_cart)
                navigateToCart()
            } else {
                val item = product?.let {
                    getCartProduct(
                        it
                    )
                }
                item?.let { t -> model.addProductToCart(t) }
                addToCart?.text = getString(R.string.view_cart)
            }
        })
    }

    override fun onResume() {
        super.onResume()
        updateCartCounter()
    }

    private fun navigateToCart() {
        openActivity(CartActivity::class.java)
    }

    private fun init() {
        back = findViewById(R.id.back)
        addToCart = findViewById(R.id.add_to_cart)
        cartCounter = findViewById(R.id.cart_count)

        back?.visibility = View.VISIBLE
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