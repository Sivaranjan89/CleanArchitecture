package com.droid.cleanarchitecture.usecases

import com.droid.cleanarchitecture.db.entity.CartProductEntity
import com.droid.cleanarchitecture.db.entity.ProductsEntity
import com.droid.cleanarchitecture.repository.ProductsRepository
import com.droid.cleanarchitecture.utils.FURNITURE
import com.droid.cleanarchitecture.utils.LAPTOP
import org.koin.core.KoinComponent
import org.koin.core.inject

class ProductsUseCase : KoinComponent {

    private val repository: ProductsRepository by inject()

    fun getProducts() = repository.getAllProducts()

    fun getProduct(product: Long) = repository.getProduct(product)

    fun filterLaptop(list: List<ProductsEntity>?): ArrayList<ProductsEntity> {
        val products: ArrayList<ProductsEntity> = ArrayList()

        list?.let {
            for (item in it) {
                if (LAPTOP.equals(item.category)) {
                    products.add(item)
                }
            }
        }

        return products
    }

    fun filterFurniture(list: List<ProductsEntity>?): ArrayList<ProductsEntity> {
        val products: ArrayList<ProductsEntity> = ArrayList()

        list?.let {
            for (item in it) {
                if (FURNITURE.equals(item.category)) {
                    products.add(item)
                }
            }
        }

        return products
    }

    fun addProductToCart(product: CartProductEntity) = repository.addProductToCart(product)
}