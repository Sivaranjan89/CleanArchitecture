package com.droid.cleanarchitecture.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = arrayOf(ProductsEntity::class, CartProductEntity::class),
    version = 1,
    exportSchema = false
)
abstract class ProductsDatabase : RoomDatabase() {

    abstract fun getProductsDao(): ProductsDao

    abstract fun getCartDao(): CartDao

    companion object {
        private var INSTANCE: ProductsDatabase? = null

        fun getInstance(context: Context): ProductsDatabase? {
            if (INSTANCE == null) {
                synchronized(ProductsDatabase::class) {
                    INSTANCE =
                        Room.databaseBuilder(context, ProductsDatabase::class.java, "products.db")
                            .build()
                }
            }

            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }

}