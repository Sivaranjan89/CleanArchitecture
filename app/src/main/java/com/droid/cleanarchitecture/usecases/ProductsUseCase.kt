package com.droid.cleanarchitecture.usecases

import com.droid.cleanarchitecture.home.model.Product
import com.droid.cleanarchitecture.home.model.ProductList
import com.droid.cleanarchitecture.pdp.model.ProductDetail
import com.droid.cleanarchitecture.repository.ProductsRepository
import com.droid.cleanarchitecture.utils.FURNITURE
import com.droid.cleanarchitecture.utils.LAPTOP
import org.koin.core.KoinComponent
import org.koin.core.inject

class ProductsUseCase : KoinComponent {

    private val repository: ProductsRepository by inject()

    fun getProducts() = repository.getCategoriesJsonData()

    fun getProduct(product: String) = repository.getProduct(product)

    fun filterLaptop(list: ProductList?): ArrayList<Product> {
        val products: ArrayList<Product> = ArrayList()

        list?.products?.let {
            for (item in it) {
                if (LAPTOP.equals(item.category)) {
                    products.add(item)
                }
            }
        }

        return products
    }

    fun filterFurniture(list: ProductList?): ArrayList<Product> {
        val products: ArrayList<Product> = ArrayList()

        list?.products?.let {
            for (item in it) {
                if (FURNITURE.equals(item.category)) {
                    products.add(item)
                }
            }
        }

        return products
    }

    fun addProductToCart(product: ProductDetail): Boolean {
        return true
    }
}