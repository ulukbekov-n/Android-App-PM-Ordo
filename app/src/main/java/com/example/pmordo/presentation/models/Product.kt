package com.example.pmordo.presentation.models
data class Product(
    val id: Int,
    val name: String,
    val brand: String,
    val country_of_origin: String,
    val description: String,
    val price: Int,
    val quantity: Int,
    val images: List<ProductImage>,
    val specifications: List<ProductSpecification>,
    val category: Int,
    val store: Int
)

data class ProductImage(
    val id: Int,
    val image: String
)

data class ProductSpecification(
    val id: Int,
    val name: String,
    val value: String
)

data class ProductApiResponse(
    val results: List<Product>
)

data class SearchProductsRequest(
    val address: String?,
    val brand: String?,
    val category: String?,
    val country: String?,
    val fuel: String?,
    val limit: Int?,
    val offset: Int?,
    val ordering: String?,
    val price__gte: Int?,
    val price__lte: Int?,
    val search: String?
)

