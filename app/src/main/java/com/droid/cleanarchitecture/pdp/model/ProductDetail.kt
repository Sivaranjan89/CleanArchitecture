package com.droid.cleanarchitecture.pdp.model

import java.io.Serializable

data class ProductDetail(
    var productName: String,
    var id: String,
    var category: String,
    var price: String,
    var wasPrice: String,
    var discount: String,
    var image: String,
    var stockAvailable: Boolean
) : Serializable {
}