package com.droid.cleanarchitecture.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.droid.cleanarchitecture.db.ProductsDao
import com.droid.cleanarchitecture.db.ProductsDatabase
import com.droid.cleanarchitecture.db.ProductsEntity
import com.droid.cleanarchitecture.home.model.ProductList
import com.droid.cleanarchitecture.pdp.model.ProductDetail
import com.droid.cleanarchitecture.utils.PRODUCTS_ALL
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject

class ProductsRepository : KoinComponent {

    val context: Context by inject()

    private var products: LiveData<List<ProductsEntity>>? = null

    private var productDao: ProductsDao?

    init {
        val db = ProductsDatabase.getInstance(context)
        productDao = db?.getProductsDao()
    }

    fun getAllProducts() = productDao?.getAllProducts()

    fun getProduct(product: String): ProductDetail? {

        val json = context.assets.open(product).bufferedReader().use {
            it.readText()
        }

        return convertJsonToProduct(json)
    }

    private fun convertJsonToProduct(jsonString: String?): ProductDetail {
        val gson = Gson()
        return gson.fromJson(jsonString, ProductDetail::class.java)
    }
}