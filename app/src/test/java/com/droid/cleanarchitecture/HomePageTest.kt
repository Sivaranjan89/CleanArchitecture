package com.droid.cleanarchitecture

import android.app.Instrumentation
import android.content.Context
import com.droid.cleanarchitecture.db.ProductsDao
import com.droid.cleanarchitecture.db.ProductsDatabase
import com.droid.cleanarchitecture.db.ProductsEntity
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
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.inject
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

@RunWith(JUnit4::class)
class HomePageTest : KoinTest {


    val useCases: ProductsUseCase by inject()

    val context: Context by inject()

    var database: ProductsDatabase? = null
    var productDao: ProductsDao? = null


    @Before
    fun setUp() {
        productDao = mock(ProductsDao::class.java)
        database = mock(ProductsDatabase::class.java)

        startKoin {
            modules(listOf(viewModelModule, usecaseModule, repoModule))
        }
    }

    @Test
    fun doesFetchProducts() {
        val item = ProductsEntity(
            productId = 1000100,
            category = "Laptop",
            productName = "Macbook Pro 15",
            price = "$2199",
            wasPrice = "$2399",
            discount = "$200",
            stockAvailable = true,
            image = "https://switch.com.my/wp-content/uploads/2018/08/MBTB1518SG.png"
        )

        productDao?.addProduct(item)

        val dbItem = productDao?.getProductFromId(item.productId)

        assert(item.equals(dbItem))
    }

    /*@Test
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
    }*/

    @After
    fun after() {
        stopKoin()
    }
}