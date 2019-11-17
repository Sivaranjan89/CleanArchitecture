package com.droid.cleanarchitecture.home.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.droid.cleanarchitecture.home.usecase.HomeUseCases
import com.mobeewave.retail.model.Product

class HomeViewModel(val useCase: HomeUseCases) : ViewModel() {

    var laptop: MutableLiveData<ArrayList<Product>> = MutableLiveData()
    var furniture: MutableLiveData<ArrayList<Product>> = MutableLiveData()

    fun fetchProducts() {
        val productList = useCase.getProducts()

        productList?.let {
            laptop.value = useCase.filterLaptop(it)
            furniture.value = useCase.filterFurniture(it)
        }
    }

}