package com.droid.cleanarchitecture.repository

import android.content.Context
import com.droid.cleanarchitecture.db.ProductsDao
import com.droid.cleanarchitecture.db.ProductsDatabase
import com.droid.cleanarchitecture.db.ProductsEntity
import com.droid.cleanarchitecture.pdp.model.ProductDetail
import com.google.gson.Gson
import org.koin.core.KoinComponent
import org.koin.core.inject

class ProductsRepository : KoinComponent {

    val context: Context by inject()

    private var productDao: ProductsDao?

    init {
        val db = ProductsDatabase.getInstance(context)
        productDao = db?.getProductsDao()
    }

    fun getAllProducts() = productDao?.getAllProducts()

    fun getProduct(product: Long): ProductsEntity? {
        return productDao?.getProductFromId(product)
    }
}