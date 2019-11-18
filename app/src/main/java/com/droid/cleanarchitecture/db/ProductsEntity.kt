package com.droid.cleanarchitecture.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Products")
data class ProductsEntity(
    @PrimaryKey @ColumnInfo var productId: Int,
    @ColumnInfo var productName: String,
    @ColumnInfo var category: String,
    @ColumnInfo var price: String,
    @ColumnInfo var wasPrice: String,
    @ColumnInfo var discount: String,
    @ColumnInfo var image: String,
    @ColumnInfo var stockAvailable: Boolean
)