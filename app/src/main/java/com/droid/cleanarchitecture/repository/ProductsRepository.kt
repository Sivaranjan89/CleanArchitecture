package com.droid.cleanarchitecture.repository

import android.content.Context
import androidx.lifecycle.MutableLiveData
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

    fun getAllProducts(): MutableLiveData<List<ProductsEntity>> {
        val db = ProductsDatabase.getInstance(context)
        var items: MutableLiveData<List<ProductsEntity>> = MutableLiveData()

        GlobalScope.launch {
            db?.getProductsDao()?.getAllProducts()?.let {
                items.value = it
            }
        }
        return items
    }

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