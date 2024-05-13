package com.michael.products.data.remote

data class ProductsData(
    val limit: Int,
    val products: List<ProductDto>,
    val skip: Int,
    val total: Int
)