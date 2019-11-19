package com.droid.cleanarchitecture

import android.os.Handler
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.droid.cleanarchitecture.db.ProductsDatabase
import com.droid.cleanarchitecture.db.dao.ProductsDao
import com.droid.cleanarchitecture.db.entity.ProductsEntity
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.stopKoin
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

@RunWith(AndroidJUnit4::class)
class ProductsTest {

    var productDao: ProductsDao? = null


    @Before
    fun setUp() {
        ProductsDatabase.TEST_MODE = true
        productDao =
            ProductsDatabase.getInstance(InstrumentationRegistry.getInstrumentation().targetContext)
                ?.getProductsDao()

        /*startKoin {
            modules(listOf(viewModelModule, usecaseModule, repoModule))
        }*/
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

        val dbItem = productDao?.getProductFromId(item.productId)?.let { getValue(it) }

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