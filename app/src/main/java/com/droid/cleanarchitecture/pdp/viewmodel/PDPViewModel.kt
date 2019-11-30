package com.droid.cleanarchitecture.pdp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.droid.cleanarchitecture.db.entity.CartProductEntity
import com.droid.cleanarchitecture.usecases.ProductsUseCase
import org.koin.core.KoinComponent
import org.koin.core.inject

class PDPViewModel : ViewModel(), KoinComponent {

    private var itemAdded: Boolean = false
    private val useCase: ProductsUseCase by inject()

    var addToCart: MutableLiveData<Boolean> = MutableLiveData()

    fun getProduct(product: Long) = useCase.getProduct(product)

    fun addProductToCart(product: CartProductEntity) = useCase.addProductToCart(product)

    fun clickAddToCart() {
        itemAdded = !itemAdded
        addToCart.value = itemAdded
    }

}