package com.droid.cleanarchitecture.repository

import android.content.Context
import com.droid.cleanarchitecture.db.*
import org.koin.core.KoinComponent
import org.koin.core.inject

class ProductsRepository : KoinComponent {

    val context: Context by inject()

    private var productDao: ProductsDao?
    private var cartDao: CartDao?

    init {
        val db = ProductsDatabase.getInstance(context)
        productDao = db?.getProductsDao()
        cartDao = db?.getCartDao()
    }

    fun getAllProducts() = productDao?.getAllProducts()

    fun getProduct(id: Long) = productDao?.getProductFromId(id)

    fun addProductToCart(product: CartProductEntity) = cartDao?.addProductToCart(product)
}