package com.droid.cleanarchitecture.home.repository

import android.content.Context
import com.droid.cleanarchitecture.utils.PRODUCTS_ALL
import com.google.gson.Gson
import com.mobeewave.retail.model.ProductList

class HomeRepository(private val context: Context) {

    fun getCategoriesJsonData(): ProductList? {

        val json = context.assets.open(PRODUCTS_ALL).bufferedReader().use {
            it.readText()
        }

        return convertJsonToDataClass(json)
    }

    private fun convertJsonToDataClass(jsonString: String?): ProductList {
        val gson = Gson()
        return gson.fromJson(jsonString, ProductList::class.java)
    }
}