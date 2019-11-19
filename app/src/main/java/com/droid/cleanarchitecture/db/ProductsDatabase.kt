package com.droid.cleanarchitecture.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.droid.cleanarchitecture.db.dao.CartDao
import com.droid.cleanarchitecture.db.dao.ProductsDao
import com.droid.cleanarchitecture.db.entity.CartProductEntity
import com.droid.cleanarchitecture.db.entity.ProductsEntity
import com.droid.cleanarchitecture.utils.PRODUCTS_DB

@Database(
    entities = arrayOf(ProductsEntity::class, CartProductEntity::class),
    version = 1,
    exportSchema = false
)
abstract class ProductsDatabase : RoomDatabase() {

    abstract fun getProductsDao(): ProductsDao

    abstract fun getCartDao(): CartDao

    companion object {
        var TEST_MODE = false
        private var INSTANCE: ProductsDatabase? = null

        fun getInstance(context: Context): ProductsDatabase? {
            if (INSTANCE == null) {
                synchronized(ProductsDatabase::class) {
                    if (TEST_MODE) {
                        INSTANCE =
                            Room.databaseBuilder(context, ProductsDatabase::class.java, PRODUCTS_DB)
                                .allowMainThreadQueries()
                                .build()
                    } else {
                        INSTANCE =
                            Room.databaseBuilder(context, ProductsDatabase::class.java, PRODUCTS_DB)
                                .build()
                    }
                }
            }

            return INSTANCE
        }
    }

}