package com.droid.cleanarchitecture.pdp.view

import android.graphics.Paint
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.droid.cleanarchitecture.R
import com.droid.cleanarchitecture.databinding.PdpActivityBinding
import com.droid.cleanarchitecture.db.entity.ProductsEntity
import com.droid.cleanarchitecture.pdp.viewmodel.PDPViewModel
import com.droid.cleanarchitecture.utils.PRODUCT
import com.droid.cleanarchitecture.utils.getCartProduct
import com.squareup.picasso.Picasso
import org.koin.core.KoinComponent
import org.koin.core.inject

class ProductDetailActivity : AppCompatActivity(), KoinComponent {

    private val model: PDPViewModel by inject()

    private var productId: Long? = 0
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

        productId?.let {
            model.getProduct(it)?.observe(this, Observer {
                product = it

                binding.product = product

                Picasso.get().load(product?.image).placeholder(R.mipmap.placeholder).into(image)
            })
        }

        addToCart?.setOnClickListener {
            if (itemAdded) {
                itemAdded = false
                addToCart?.text = getString(R.string.add_to_cart)
                navigateToCart()
            } else {
                val item = product?.let { getCartProduct(it) }
                item?.let { t -> model.addProductToCart(t) }
                itemAdded = true
                addToCart?.text = getString(R.string.view_cart)
            }
        }
    }

    private fun navigateToCart() {
        TODO()
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