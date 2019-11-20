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

    lateinit var db: ProductsDatabase
    lateinit var dao: ProductsDao


    @Before
    fun setUp() {
        db = mock(ProductsDatabase::class.java)
        dao = db.getProductsDao()

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

        dao.addProduct(item)

        val dbItem = dao.getProductFromId(item.productId)

        assert(item.equals(dbItem))
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


    @Throws(InterruptedException::class)
    fun <T> getValue(liveData: LiveData<T>): T {
        val data = arrayOfNulls<Any>(1)
        val latch = CountDownLatch(1)
        val observer = object : Observer<T> {
            override fun onChanged(t: T?) {
                data[0] = t
                latch.countDown()
                liveData.removeObserver(this)//To change body of created functions use File | Settings | File Templates.
            }

        }
        liveData.observeForever(observer)
        latch.await(2, TimeUnit.SECONDS)

        return data[0] as T
    }

    @After
    fun after() {
        stopKoin()
    }
}