package com.example.pmordo.presentation.ui.product

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.pmordo.R
import com.example.pmordo.databinding.FragmentHomeBinding
import com.example.pmordo.databinding.ItemProductBinding
import com.example.pmordo.databinding.ItemViewFragmentBinding
import com.example.pmordo.presentation.models.CartManager
import com.example.pmordo.presentation.models.Product

class ItemViewFragment : Fragment() {
    private lateinit var binding: ItemViewFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ItemViewFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val product = arguments?.getParcelable<Product>("product")

        if (product != null) {
            binding.productName.text = product.name
            binding.productPrice.text = "$" + product.price.toString()
            binding.productDescription.text = "Description: " + product.description
            binding.productQuantity.text = "Quantity: " + product.quantity.toString()


            if (product.images.isNotEmpty()) {
                val firstImage = product.images[0]
            }
        }
        val addToCartButton = view.findViewById<Button>(R.id.addToCartButton)
        addToCartButton.setOnClickListener {
            val product = arguments?.getParcelable<Product>("product")
            if (product != null) {
                CartManager.addToCart(product)
                // Notify the user that the product has been added to the cart (e.g., with a Toast message).
            }
        }
    }

}
