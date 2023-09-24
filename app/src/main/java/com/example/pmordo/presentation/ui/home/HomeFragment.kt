package com.example.pmordo.presentation.ui.home

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.appcompat.widget.SearchView
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
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
        val imageSlider  = view.findViewById<ImageSlider>(R.id.image_slider)
        val imageList = ArrayList<SlideModel>()
        imageList.add(SlideModel("https://kuhnoteka.ru/wp-content/uploads/2017/10/0482.jpg",""))
        imageList.add(SlideModel("https://ekbkupe.ru/images/stories/virtuemart/product/2022-03-05_11-24-055.jpg",""))
        imageList.add(SlideModel("https://www.cucine.ru/upload/resize_cache/iblock/214/1200_878_16b651e4f962b08687c210ba0fcd27736/miton-cucine-limha-eurolux-1.jpg",""))
        imageList.add(SlideModel("https://dizainexpert.ru/wp-content/uploads/2020/07/kuhnya-haj-tek.jpg",""))
//        imageList.add(SlideModel("",""))
        imageSlider.setImageList(imageList, ScaleTypes.FIT)
        productList = generateProductList()

        productAdapter = ProductAdapter(productList) // Initialize the adapter with the product list

        val gridLayoutManager = GridLayoutManager(requireContext(), 2)
        binding().productRecyclerView.layoutManager = gridLayoutManager
        binding().productRecyclerView.adapter = productAdapter

        binding().becomeSeller.setOnClickListener { findNavController().navigate(R.id.action_homeFragment_to_sellerRegisterFragment2)}

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
