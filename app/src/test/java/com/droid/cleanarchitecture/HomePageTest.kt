package com.droid.cleanarchitecture

import com.droid.cleanarchitecture.di.repoModule
import com.droid.cleanarchitecture.di.usecaseModule
import com.droid.cleanarchitecture.di.viewModelModule
import com.droid.cleanarchitecture.home.model.ProductList
import com.droid.cleanarchitecture.repository.ProductsRepository
import com.droid.cleanarchitecture.usecases.ProductsUseCase
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.inject
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock


class HomePageTest : KoinTest {


    val useCases: ProductsUseCase by inject()


    @Before
    fun setUp() {
        startKoin {
            modules(listOf(viewModelModule, usecaseModule, repoModule))
        }
    }

    @Test
    fun doesFetchProducts() {
        val homeRepository = mock(ProductsRepository::class.java)
        val productList = mock(ProductList::class.java)
        `when`(homeRepository.getCategoriesJsonData()).thenReturn(productList)
    }

    @Test
    fun filterLaptopWithNull() {
        val list = useCases.filterLaptop(null)
        Assert.assertNotNull(list)
    }

    @Test
    fun filterFurnitureWithNull() {
        val list = useCases.filterFurniture(null)
        Assert.assertNotNull(list)
    }

    @After
    fun after() {
        stopKoin()
    }
}