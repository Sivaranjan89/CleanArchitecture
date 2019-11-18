package com.droid.cleanarchitecture.db

import androidx.room.*

@Dao
interface ProductsDao {

    @Query("SELECT * FROM Products")
    fun getAllProducts(): List<ProductsEntity>

    @Query("SELECT * FROM Products WHERE productId LIKE :id")
    fun getProductFromId(id: Int): ProductsEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addProduct(product: ProductsEntity)

    @Delete
    fun deleteProduct(product: ProductsEntity)

    @Update
    fun updateProduct(product: ProductsEntity)
}