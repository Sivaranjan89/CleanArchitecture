package com.droid.cleanarchitecture.home.usecase

import com.droid.cleanarchitecture.home.repository.HomeRepository
import com.droid.cleanarchitecture.utils.FURNITURE
import com.droid.cleanarchitecture.utils.LAPTOP
import com.mobeewave.retail.model.Product
import com.mobeewave.retail.model.ProductList

class HomeUseCases(private val repository: HomeRepository) {

    fun getProducts() = repository.getCategoriesJsonData()

    fun filterLaptop(list: ProductList): ArrayList<Product> {
        val products: ArrayList<Product> = ArrayList()

        list.products?.let {
            for (item in it) {
                if (LAPTOP.equals(item.category)) {
                    products.add(item)
                }
            }
        }

        return products
    }

    fun filterFurniture(list: ProductList): ArrayList<Product> {
        val products: ArrayList<Product> = ArrayList()

        list.products?.let {
            for (item in it) {
                if (FURNITURE.equals(item.category)) {
                    products.add(item)
                }
            }
        }

        return products
    }
}