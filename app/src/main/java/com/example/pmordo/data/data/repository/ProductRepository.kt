package com.example.pmordo.data.data.repository

import com.example.pmordo.data.data.service.ProductApi
import com.example.pmordo.presentation.models.Product
import com.example.pmordo.presentation.models.ProductApiResponse
import com.example.pmordo.presentation.models.SearchProductsRequest
import retrofit2.Response

class ProductRepository(private val productApi: ProductApi) {

    suspend fun searchProducts(request: SearchProductsRequest): Response<ProductApiResponse> {
        return productApi.searchProducts(
            request.address,
            request.brand,
            request.category,
            request.country,
            request.fuel,
            request.limit,
            request.offset,
            request.ordering,
            request.price__gte,
            request.price__lte,
            request.search
        )
    }

    suspend fun getProducts(): List<Product> {
        try {
            val request = SearchProductsRequest(
                address = "",
                brand = "",
                category = "",
                country = "",
                fuel = "",
                limit = 10000,
                offset = 0,
                ordering = "",
                price__gte = 0,
                price__lte = Int.MAX_VALUE,
                search = ""
            )

            val response = searchProducts(request)

            if (response.isSuccessful) {
                val productApiResponse = response.body()
                return productApiResponse?.results ?: emptyList()
            } else {
                // Handle the error, e.g., log the error or throw an exception
                throw Exception("Failed to fetch products")
            }
        } catch (e: Exception) {
            // Handle exceptions, e.g., network issues
            e.printStackTrace()
            throw Exception("Network error")
        }
    }
}
