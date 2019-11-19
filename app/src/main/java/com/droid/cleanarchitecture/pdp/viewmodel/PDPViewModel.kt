package com.droid.cleanarchitecture.pdp.viewmodel

import androidx.lifecycle.ViewModel
import com.droid.cleanarchitecture.db.entity.CartProductEntity
import com.droid.cleanarchitecture.usecases.ProductsUseCase
import org.koin.core.KoinComponent
import org.koin.core.inject

class PDPViewModel() : ViewModel(), KoinComponent {

    private val useCase: ProductsUseCase by inject()

    fun getProduct(product: Long) = useCase.getProduct(product)

    fun addProductToCart(product: CartProductEntity) = useCase.addProductToCart(product)

}