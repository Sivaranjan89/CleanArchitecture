package com.droid.cleanarchitecture

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.droid.cleanarchitecture.db.ProductsDatabase
import com.droid.cleanarchitecture.db.dao.ProductsDao
import com.droid.cleanarchitecture.db.entity.ProductsEntity
import com.droid.cleanarchitecture.di.repoModule
import com.droid.cleanarchitecture.di.usecaseModule
import com.droid.cleanarchitecture.di.viewModelModule
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
import org.mockito.Mockito.mock
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

@RunWith(JUnit4::class)
class HomePageTest : KoinTest {


    val useCases: ProductsUseCase by inject()

    var db: ProductsDatabase = mock(ProductsDatabase::class.java)
    lateinit var dao: ProductsDao


    @Before
    fun setUp() {
        dao = db.getProductsDao()

        startKoin {
            modules(listOf(viewModelModule, usecaseModule, repoModule))
        }
    }

    @Test
    fun verifyFetchProducts() {
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

        dao.addProduct(item)

        val dbItem = dao.getProductFromId(item.productId)

        assert(item.equals(dbItem))
    }

    /*@Test
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