package com.droid.cleanarchitecture.home.repository

import androidx.lifecycle.MutableLiveData
import com.droid.cleanarchitecture.CAApplication
import com.droid.cleanarchitecture.utils.PRODUCTS_ALL
import com.google.gson.Gson
import com.mobeewave.retail.model.ProductList

class HomeRepository {

    fun getCategoriesJsonData(): ProductList? {

        val json = CAApplication.getContext().assets.open(PRODUCTS_ALL).bufferedReader().use {
            it.readText()
        }

        return convertJsonToDataClass(json)
    }

    private fun convertJsonToDataClass(jsonString: String?): ProductList {
        val gson = Gson()
        return gson.fromJson(jsonString, ProductList::class.java)
    }
}