package com.michael.products.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface ProductApi {
    @GET("products")
    suspend fun getProducts(
        @Query("page") page: Int,
        @Query("per_page") pageCount: Int
    ): ProductsData

    companion object {
        const val BASE_URL = "https://dummyjson.com/"
    }

}