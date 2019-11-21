package com.droid.cleanarchitecture.utils.extensions

import com.droid.cleanarchitecture.db.entity.CartProductEntity
import com.droid.cleanarchitecture.db.entity.ProductsEntity

fun getCartProduct(product: ProductsEntity): CartProductEntity {
    return CartProductEntity(
        productId = product.productId,
        productName = product.productName,
        category = product.category,
        price = product.price,
        wasPrice = product.wasPrice,
        discount = product.discount,
        image = product.image,
        stockAvailable = product.stockAvailable,
        quantity = 1
    )
}

fun generateAllProducts(): List<ProductsEntity> {
    val products: MutableList<ProductsEntity> = ArrayList()

    products.add(
        ProductsEntity(
            productId = 1000100,
            category = "Laptop",
            productName = "Macbook Pro 15",
            price = "$2199",
            wasPrice = "$2399",
            discount = "$200",
            stockAvailable = true,
            image = "https://switch.com.my/wp-content/uploads/2018/08/MBTB1518SG.png"
        )
    )

    products.add(
        ProductsEntity(
            productId = 1000101,
            category = "Laptop",
            productName = "Macbook Pro 13",
            price = "$1899",
            wasPrice = "$1999",
            discount = "$100",
            stockAvailable = true,
            image = "https://switch.com.my/wp-content/uploads/2017/11/macbookpro_13_spacegray.png"
        )
    )

    products.add(
        ProductsEntity(
            productId = 1000102,
            category = "Laptop",
            productName = "Sony Waio",
            price = "$1399",
            wasPrice = "$1459",
            discount = "$60",
            stockAvailable = true,
            image = "https://images.reevoo.com/products/585/585030/original.png?fingerprint=3e992fb6e393569980270511187b88cf3f599363"
        )
    )

    products.add(
        ProductsEntity(
            productId = 1000103, category = "Laptop", productName = "HP ", price = "$999",
            wasPrice = "$1089", discount = "$90", stockAvailable = false,
            image = "https://www.pngarts.com/files/4/HP-Laptop-Transparent-Image.png"
        )
    )

    products.add(
        ProductsEntity(
            productId = 1000104, category = "Furniture", productName = "Table", price = "$48",
            wasPrice = "$60", discount = "$12", stockAvailable = true,
            image = "https://www.pngsee.com/uploadpng/full/82-827193_4-seater-dining-set-with-slatted-chair-back.png"
        )
    )

    products.add(
        ProductsEntity(
            productId = 1000105, category = "Furniture", productName = "Chair", price = "$15",
            wasPrice = "$30", discount = "$15", stockAvailable = true,
            image = "https://i0.wp.com/sreditingzone.com/wp-content/uploads/2018/04/Chair-Png-11.png?resize=768%2C319&ssl=1"
        )
    )

    products.add(
        ProductsEntity(
            productId = 1000106, category = "Furniture", productName = "Almirah", price = "$89",
            wasPrice = "$100", discount = "$11", stockAvailable = true,
            image = "https://purepng.com/public/uploads/large/purepng.com-cupboardcupboardpressa-cabinetdoor-and-shelves-1701527922642nlm5a.png"
        )
    )

    products.add(
        ProductsEntity(
            productId = 1000107,
            category = "Furniture",
            productName = "Dining Table ",
            price = "$62",
            wasPrice = "$89",
            discount = "$27",
            stockAvailable = false,
            image = "https://www.sccpre.cat/mypng/detail/13-135605_dining-set-table-with-4-chairs-png-dining.png"
        )
    )

    return products
}