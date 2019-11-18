package com.droid.cleanarchitecture.repository

import android.content.Context
import com.droid.cleanarchitecture.home.model.ProductList
import com.droid.cleanarchitecture.pdp.model.ProductDetail
import com.droid.cleanarchitecture.utils.PRODUCTS_ALL
import com.google.gson.Gson
import org.koin.core.KoinComponent
import org.koin.core.inject

class ProductsRepository : KoinComponent {

    val context: Context by inject()

    fun getCategoriesJsonData(): ProductList? {

        val json = context.assets.open(PRODUCTS_ALL).bufferedReader().use {
            it.readText()
        }

        return convertJsonToDataClass(json)
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

    private fun convertJsonToDataClass(jsonString: String?): ProductList {
        val gson = Gson()
        return gson.fromJson(jsonString, ProductList::class.java)
    }
}