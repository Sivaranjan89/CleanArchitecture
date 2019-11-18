package com.droid.cleanarchitecture.pdp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.droid.cleanarchitecture.pdp.model.ProductDetail
import com.droid.cleanarchitecture.usecases.ProductsUseCase
import org.koin.core.KoinComponent
import org.koin.core.inject

class PDPViewModel() : ViewModel(), KoinComponent {

    private val useCase: ProductsUseCase by inject()

    var cartSuccess: MutableLiveData<Boolean> = MutableLiveData()

    fun getProduct(product: String) = useCase.getProduct(product)

    fun addProductToCart(product: ProductDetail) {
        cartSuccess.value = useCase.addProductToCart(product)
    }

}