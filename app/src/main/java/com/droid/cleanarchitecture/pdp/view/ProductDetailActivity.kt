package com.droid.cleanarchitecture.pdp.view

import android.graphics.Paint
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.droid.cleanarchitecture.R
import com.droid.cleanarchitecture.databinding.PdpActivityBinding
import com.droid.cleanarchitecture.db.ProductsEntity
import com.droid.cleanarchitecture.pdp.model.ProductDetail
import com.droid.cleanarchitecture.pdp.viewmodel.PDPViewModel
import com.droid.cleanarchitecture.utils.PRODUCT
import com.squareup.picasso.Picasso
import org.koin.core.KoinComponent
import org.koin.core.inject

class ProductDetailActivity : AppCompatActivity(), KoinComponent {

    private var productId: Long? = 0
    private val model: PDPViewModel by inject()
    private var product: ProductsEntity? = null
    private var image: ImageView? = null
    private var back: ImageView? = null
    private var wasPrice: TextView? = null
    private var addToCart: Button? = null
    private lateinit var binding: PdpActivityBinding
    private var itemAdded: Boolean = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.pdp_activity)

        init()

        product = productId?.let { model.getProduct(it) }

        binding.product = product

        Picasso.get().load(product?.image).placeholder(R.mipmap.placeholder)
            .into(image)

        addToCart?.setOnClickListener {
            if (itemAdded) {
                itemAdded = false
                addToCart?.text = getString(R.string.add_to_cart)
                navigateToCart()
            } else {
                //product?.let { it1 -> model.addProductToCart(it1) }
            }
        }

        model.cartSuccess.observe(this, Observer { success ->
            itemAdded = success
            if (success) {
                addToCart?.text = getString(R.string.view_cart)
            } else {
                Toast.makeText(this, getString(R.string.add_failure_to_cart), Toast.LENGTH_SHORT)
                    .show()
            }
        })
    }

    private fun navigateToCart() {

    }

    private fun init() {
        productId = intent.getLongExtra(PRODUCT, 0)
        image = findViewById(R.id.product_image)
        back = findViewById(R.id.back)
        wasPrice = findViewById(R.id.product_was_price)
        addToCart = findViewById(R.id.add_to_cart)

        back?.visibility = View.VISIBLE
        wasPrice?.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
    }
}