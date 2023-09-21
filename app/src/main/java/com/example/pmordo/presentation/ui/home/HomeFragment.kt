package com.example.pmordo.presentation.ui.home

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.appcompat.widget.SearchView
import com.example.pmordo.R
import com.example.pmordo.databinding.FragmentHomeBinding
import com.example.pmordo.presentation.base.BaseFragment
import com.example.pmordo.presentation.models.Product
import com.example.pmordo.presentation.models.ProductImage
import com.example.pmordo.presentation.models.ProductSpecification
import com.example.pmordo.presentation.ui.activity_main.MainActivity
import com.example.pmordo.presentation.ui.product.ProductAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment :
    BaseFragment<FragmentHomeBinding, HomeViewModel>(FragmentHomeBinding::inflate) {
    override val viewModel: HomeViewModel by viewModel()
    private lateinit var searchView: SearchView
    private var productList: List<Product> = emptyList()
    private lateinit var productAdapter: ProductAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as MainActivity).setDrawerVisibility(true)
        showBottomNavigationView()

        productList = generateProductList()

        productAdapter = ProductAdapter(productList) // Initialize the adapter with the product list

        val gridLayoutManager = GridLayoutManager(requireContext(), 2)
        binding().productRecyclerView.layoutManager = gridLayoutManager
        binding().productRecyclerView.adapter = productAdapter

        searchView = view.findViewById(R.id.searchView)
        setupSearchView(searchView)
    }

    private fun setupSearchView(searchView: SearchView) {
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                // Filter products based on the search query
                filterProducts(newText.orEmpty())
                return true
            }
        })
    }

    private fun filterProducts(query: String) {
        val filteredList = productList.filter { product ->
            product.name.contains(query, ignoreCase = true) ||
                    product.description.contains(query, ignoreCase = true)
        }

        // Update the RecyclerView with the filtered list
        productAdapter.updateProductList(filteredList)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        (requireActivity() as MainActivity).setDrawerVisibility(false)
    }

    private fun generateProductList(): List<Product> {
        val productList = mutableListOf<Product>()

        for (i in 1..10) {
            val product = Product(
                name = "Product $i",
                brand = "Brand $i",
                country_of_origin = "Country $i",
                description = "Description for Product $i",
                price = 11110,
                quantity = 846343568,
                images = listOf(ProductImage("image_url")),
                specifications = listOf(ProductSpecification("Spec $i", "Value $i")),
                category = 1,
                store = 1
            )
            productList.add(product)
        }

        return productList
    }
}
