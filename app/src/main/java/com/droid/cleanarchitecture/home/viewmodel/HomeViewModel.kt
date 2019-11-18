package com.droid.cleanarchitecture.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.droid.cleanarchitecture.db.ProductsEntity
import com.droid.cleanarchitecture.di.usecaseModule
import com.droid.cleanarchitecture.home.model.Product
import com.droid.cleanarchitecture.usecases.ProductsUseCase
import org.koin.core.KoinComponent
import org.koin.core.inject

class HomeViewModel() : ViewModel(), KoinComponent {

    private val useCase: ProductsUseCase by inject()

    var laptop: MutableLiveData<ArrayList<ProductsEntity>> = MutableLiveData()
    var furniture: MutableLiveData<ArrayList<ProductsEntity>> = MutableLiveData()
    var clickedProduct: MutableLiveData<ProductsEntity> = MutableLiveData()


    fun getProducts() = useCase.getProducts()

    fun filterLaptop(list: List<ProductsEntity>?) {
        laptop.value = list?.let { useCase.filterLaptop(it) }
    }

    fun filterFurniture(list: List<ProductsEntity>?) {
        furniture.value = list?.let { useCase.filterFurniture(it) }
    }

}