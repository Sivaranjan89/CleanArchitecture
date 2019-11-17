package com.droid.cleanarchitecture.home.viewmodel

import androidx.lifecycle.ViewModel
import com.droid.cleanarchitecture.home.usecase.HomeUseCases
import com.mobeewave.retail.model.Product
import org.koin.core.KoinComponent
import org.koin.core.inject

class HomeViewModel() : ViewModel(), KoinComponent {

    private val useCase: HomeUseCases by inject()

    var laptop: ArrayList<Product> = ArrayList()
    var furniture: ArrayList<Product> = ArrayList()

    fun fetchProducts() {
        useCase.getProducts()?.let {
            laptop = useCase.filterLaptop(it)
            furniture = useCase.filterFurniture(it)
        }
    }

}