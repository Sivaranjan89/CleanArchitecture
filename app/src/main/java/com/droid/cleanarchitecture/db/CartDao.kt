package com.droid.cleanarchitecture.db

import androidx.lifecycle.LiveData
import androidx.room.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@Dao
abstract class CartDao {

    @Query("SELECT * FROM cart")
    abstract fun getAllProductsFromCart(): LiveData<List<CartProductEntity>>

    @Query("SELECT * FROM cart WHERE productId = :id")
    abstract fun getProductFromId(id: Long): CartProductEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun addProduct(product: CartProductEntity)


    fun addProductToCart(product: CartProductEntity) {
        GlobalScope.launch {
            val cartProduct = getProductFromId(product.productId)

            if (cartProduct != null && cartProduct.productId == product.productId) {
                cartProduct.quantity = cartProduct.quantity.plus(1)
                addProduct(cartProduct)
            } else {
                addProduct(product)
            }
        }
    }

    @Delete
    abstract fun deleteProductFromCart(product: CartProductEntity)
}