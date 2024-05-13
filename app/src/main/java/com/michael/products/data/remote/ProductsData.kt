package com.michael.products.data.remote

import com.squareup.moshi.Json

data class ProductsData(
    @Json(name = "products") val products: List<ProductDto>,
    @Json(name = "limit") val limit: Int,
    @Json(name = "skip") val skip: Int,
    @Json(name = "total") val total: Int
)