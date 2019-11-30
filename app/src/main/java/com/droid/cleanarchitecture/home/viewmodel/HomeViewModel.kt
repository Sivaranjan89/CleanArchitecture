package com.droid.cleanarchitecture.home.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.droid.cleanarchitecture.db.ProductsDatabase
import com.droid.cleanarchitecture.db.entity.ProductsEntity
import com.droid.cleanarchitecture.usecases.ProductsUseCase
import org.koin.core.KoinComponent
import org.koin.core.inject

class HomeViewModel() : ViewModel(), KoinComponent {

    private val useCase: ProductsUseCase by inject()

    var list: List<ProductsEntity>? = null
    var clickedProduct: MutableLiveData<ProductsEntity> = MutableLiveData()


    fun getProducts() = useCase.getProducts()

    fun filterProducts(name: String) = useCase.filterProducts(list, name)

    fun prePopulateProducts(database: ProductsDatabase?) = useCase.loadProductsIntoDB(database)

    fun itemClicked(product: ProductsEntity) {
        clickedProduct.value = product
    }

}