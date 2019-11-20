package com.droid.cleanarchitecture.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.droid.cleanarchitecture.db.entity.ProductsEntity

@Dao
interface ProductsDao {

    @Query("SELECT * FROM Products")
    fun getAllProducts(): LiveData<List<ProductsEntity>>

    @Query("SELECT * FROM Products WHERE productId = :id")
    fun getProductFromId(id: Long): LiveData<ProductsEntity>

    @Query("SELECT * FROM Products WHERE productId = :id")
    fun getProductFromIdForTest(id: Long): ProductsEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addProduct(product: ProductsEntity)

    @Delete
    fun deleteProduct(product: ProductsEntity)

    @Update
    fun updateProduct(product: ProductsEntity)
}