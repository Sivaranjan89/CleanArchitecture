package com.droid.cleanarchitecture.usecases

import com.droid.cleanarchitecture.db.ProductsDatabase
import com.droid.cleanarchitecture.db.entity.CartProductEntity
import com.droid.cleanarchitecture.db.entity.ProductsEntity
import com.droid.cleanarchitecture.repository.ProductsRepository
import com.droid.cleanarchitecture.utils.generateAllProducts
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject

class ProductsUseCase : KoinComponent {

    private val repository: ProductsRepository by inject()

    fun getProducts() = repository.getAllProducts()

    fun getProduct(product: Long) = repository.getProduct(product)

    fun filterProducts(
        list: List<ProductsEntity>?,
        categoryName: String
    ): ArrayList<ProductsEntity> {
        val products: ArrayList<ProductsEntity> = ArrayList()

        list?.let {
            for (item in it) {
                if (categoryName.equals(item.category)) {
                    products.add(item)
                }
            }
        }

        return products
    }

    fun addProductToCart(product: CartProductEntity) = repository.addProductToCart(product)

    fun loadProductsIntoDB(database: ProductsDatabase?) {
        val products = generateAllProducts()

        for (item in products) {
            GlobalScope.launch {
                database?.getProductsDao()?.addProduct(item)
            }
        }
    }
}