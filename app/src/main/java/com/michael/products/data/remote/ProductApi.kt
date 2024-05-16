package com.michael.products.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface ProductApi {
    @GET("products")
    suspend fun getProducts(
        @Query("skip") skip: Int,
        @Query("limit") limit: Int,
    ): ProductsData

    companion object {
        const val BASE_URL = "https://dummyjson.com/"
    }

}