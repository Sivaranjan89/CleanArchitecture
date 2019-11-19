package com.droid.cleanarchitecture.utils

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.droid.cleanarchitecture.db.CartProductEntity
import com.droid.cleanarchitecture.db.ProductsEntity


fun FragmentManager.inTransaction(func: FragmentTransaction.() -> Unit) {
    val fragmentTransaction = beginTransaction()
    fragmentTransaction.func()
    fragmentTransaction.commit()
}

fun <T> Context.openActivity(it: Class<T>, extras: Bundle.() -> Unit = {}) {
    val intent = Intent(this, it)
    intent.putExtras(Bundle().apply(extras))
    startActivity(intent)
}

fun getCartProduct(product: ProductsEntity): CartProductEntity {
    return CartProductEntity(
        productId = product.productId,
        productName = product.productName,
        category = product.category,
        price = product.price,
        wasPrice = product.wasPrice,
        discount = product.discount,
        image = product.image,
        stockAvailable = product.stockAvailable,
        quantity = 1
    )
}