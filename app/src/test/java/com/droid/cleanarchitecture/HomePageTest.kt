package com.droid.cleanarchitecture

import com.droid.cleanarchitecture.di.appModule
import com.droid.cleanarchitecture.home.usecase.HomeUseCases
import org.junit.Before
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.test.KoinTest
import org.koin.test.inject


class HomePageTest : KoinTest {

    val useCases: HomeUseCases by inject()

    @Before
    fun setUp() {
        startKoin {
            modules(appModule)
        }
    }

    @Test
    fun doesFetchProducts() {
        val list = useCases.getProducts()

        list?.products?.let {
            assert(it.size > 0)
        }
    }
}