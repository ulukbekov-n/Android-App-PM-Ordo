package com.example.pmordo.presentation.models

object CartManager {
    private val cartItems: MutableList<Product> = mutableListOf()

    fun getCartItems(): List<Product> {
        return cartItems
    }

    fun addToCart(product: Product) {
        cartItems.add(product)
    }

    fun removeFromCart(product: Product) {
        cartItems.remove(product)
    }

    fun clearCart() {
        cartItems.clear()
    }
}
