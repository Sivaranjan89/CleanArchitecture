package com.mobeewave.retail.model

import java.io.Serializable

data class Product(
    var productName: String,
    var id: String,
    var category: String,
    var price: String,
    var image: String
) : Serializable {
}