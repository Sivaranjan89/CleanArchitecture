package com.droid.cleanarchitecture.di

import com.droid.cleanarchitecture.home.viewmodel.HomeViewModel
import com.droid.cleanarchitecture.pdp.viewmodel.PDPViewModel
import com.droid.cleanarchitecture.repository.ProductsRepository
import com.droid.cleanarchitecture.usecases.ProductsUseCase
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module


val viewModelModule = module {
    viewModel {
        HomeViewModel()
    }
    viewModel {
        PDPViewModel()
    }
}

val usecaseModule = module {
    factory {
        ProductsUseCase()
    }
}

val repoModule = module {
    factory {
        ProductsRepository()
    }
}
