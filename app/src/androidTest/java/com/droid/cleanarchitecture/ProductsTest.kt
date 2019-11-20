package com.droid.cleanarchitecture

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.droid.cleanarchitecture.db.ProductsDatabase
import com.droid.cleanarchitecture.db.dao.CartDao
import com.droid.cleanarchitecture.db.dao.ProductsDao
import com.droid.cleanarchitecture.db.entity.ProductsEntity
import com.droid.cleanarchitecture.usecases.ProductsUseCase
import com.droid.cleanarchitecture.utils.generateAllProducts
import com.droid.cleanarchitecture.utils.getCartProduct
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.KoinComponent
import org.koin.core.inject

@RunWith(AndroidJUnit4::class)
class ProductsTest : KoinComponent {

    val useCase: ProductsUseCase by inject()

    var database: ProductsDatabase? = null
    var productDao: ProductsDao? = null
    var cartDao: CartDao? = null
    lateinit var products: List<ProductsEntity>


    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        products = generateAllProducts()

        ProductsDatabase.TEST_MODE = false
        database = ProductsDatabase.getInstance(context)
        productDao = database?.getProductsDao()
        cartDao = database?.getCartDao()
    }

    @Test
    fun checkProductInserted() {
        val product = products.get(0)

        productDao?.addProduct(product)

        val dbItem = productDao?.getProductFromIdForTest(product.productId)

        Assert.assertEquals(dbItem, product)
    }

    @Test
    fun checkProductDeleted() {
        val product = products.get(0)

        productDao?.deleteProduct(product)

        val dbItem = productDao?.getProductFromIdForTest(product.productId)

        Assert.assertNull(dbItem)
    }

    @Test
    fun filterLaptopWithNull() {
        val list = useCase.filterLaptop(null)
        Assert.assertNotNull(list)
    }

    @Test
    fun filterFurnitureWithNull() {
        val list = useCase.filterFurniture(null)
        Assert.assertNotNull(list)
    }

    @Test
    fun filterLaptopWithData() {
        val list = useCase.filterLaptop(products)
        Assert.assertNotNull(list)
    }

    @Test
    fun filterFurnitureWithData() {
        val list = useCase.filterFurniture(products)
        Assert.assertNotNull(list)
    }

    @Test
    fun checkProductAddedToCart() {
        val product = products.get(0)

        cartDao?.addProduct(getCartProduct(product))

        val dbItem = cartDao?.getProductFromId(product.productId)

        Assert.assertEquals(dbItem?.productId, product.productId)
    }

    @After
    fun after() {
        database?.close()
    }
}