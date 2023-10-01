package com.example.pmordo.data.data.service

import com.example.pmordo.presentation.models.Product
import com.example.pmordo.presentation.models.ProductApiResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Query

interface ProductApi {

    @GET("/api/v1/stores/products/")

    suspend fun searchProducts(
        @Query("address") address: String?,
        @Query("brand") brand: String?,
        @Query("category") category: String?,
        @Query("country") country: String?,
        @Query("fuel") fuel: String?,
        @Query("limit") limit: Int?,
        @Query("offset") offset: Int?,
        @Query("ordering") ordering: String?,
        @Query("price__gte") price__gte: Int?,
        @Query("price__lte") price__lte: Int?,
        @Query("search") search: String?
    ): Response<ProductApiResponse>

    @GET("/api/v1/stores/products/")
    suspend fun getProducts(): Call<MutableList<Product>>
}
