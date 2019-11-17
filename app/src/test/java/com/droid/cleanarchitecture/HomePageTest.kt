package com.droid.cleanarchitecture

import com.droid.cleanarchitecture.di.appModule
import com.droid.cleanarchitecture.di.context
import com.droid.cleanarchitecture.home.repository.HomeRepository
import com.droid.cleanarchitecture.home.usecase.HomeUseCases
import com.mobeewave.retail.model.Product
import com.mobeewave.retail.model.ProductList
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


    val useCases: HomeUseCases by inject()


    @Before
    fun setUp() {
        startKoin {
            modules(listOf(appModule))
        }
    }

    @Test
    fun doesFetchProducts() {
        val homeRepository = mock(HomeRepository::class.java)
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