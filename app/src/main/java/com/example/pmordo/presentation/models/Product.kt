package com.example.pmordo.presentation.models
data class Product(
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
    val image: String
)

data class ProductSpecification(
    val name: String,
    val value: String
)
