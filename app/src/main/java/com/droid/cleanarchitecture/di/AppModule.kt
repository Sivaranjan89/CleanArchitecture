package com.droid.cleanarchitecture.di

import com.droid.cleanarchitecture.home.repository.HomeRepository
import com.droid.cleanarchitecture.home.usecase.HomeUseCases
import com.droid.cleanarchitecture.home.viewmodel.HomeViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module


val appModule = module {
    viewModel {
        HomeViewModel()
    }
    factory {
        HomeUseCases()
    }
    factory {
        HomeRepository()
    }
}

val context = module {
    single {
        androidContext()
    }
}
