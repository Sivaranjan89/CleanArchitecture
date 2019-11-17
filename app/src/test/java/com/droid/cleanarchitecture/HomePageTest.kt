package com.droid.cleanarchitecture

import com.droid.cleanarchitecture.home.usecase.HomeUseCases
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class HomePageTest {

    lateinit var useCases: HomeUseCases


    @Before
    fun setUp() {
        //useCases = HomeUseCases()
    }

    @Test
    fun doesFetchProducts() {
        val list = useCases.getProducts()

        list?.products?.let {
            assert(it.size > 0)
        }
    }
}