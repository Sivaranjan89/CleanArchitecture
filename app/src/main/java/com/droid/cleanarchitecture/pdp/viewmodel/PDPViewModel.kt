package com.droid.cleanarchitecture.pdp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.droid.cleanarchitecture.db.CartProductEntity
import com.droid.cleanarchitecture.usecases.ProductsUseCase
import org.koin.core.KoinComponent
import org.koin.core.inject

class PDPViewModel() : ViewModel(), KoinComponent {

    private val useCase: ProductsUseCase by inject()

    var cartSuccess: LiveData<Boolean>? = null

    fun getProduct(product: Long) = useCase.getProduct(product)

    fun addProductToCart(product: CartProductEntity) = useCase.addProductToCart(product)

}