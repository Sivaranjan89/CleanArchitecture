package com.droid.cleanarchitecture.home.repository

import android.content.Context
import com.droid.cleanarchitecture.utils.PRODUCTS_ALL
import com.google.gson.Gson
import com.mobeewave.retail.model.ProductList
import org.koin.core.KoinComponent
import org.koin.core.inject

class HomeRepository : KoinComponent {

    val context: Context by inject()

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