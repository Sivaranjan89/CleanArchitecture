package com.droid.cleanarchitecture.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cart")
data class CartProductEntity(
    @ColumnInfo var productId: Long,
    @ColumnInfo var productName: String,
    @ColumnInfo var category: String,
    @ColumnInfo var price: String,
    @ColumnInfo var wasPrice: String,
    @ColumnInfo var discount: String,
    @ColumnInfo var image: String,
    @ColumnInfo var stockAvailable: Boolean,
    @ColumnInfo var quantity: Int
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}