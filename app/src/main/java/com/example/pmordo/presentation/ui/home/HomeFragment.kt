package com.example.pmordo.presentation.ui.home

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.example.pmordo.R
import com.example.pmordo.data.data.service.ProductApi
import com.example.pmordo.data.data.service.ServiceGenerator
import com.example.pmordo.databinding.FragmentHomeBinding
import com.example.pmordo.presentation.base.BaseFragment
import com.example.pmordo.presentation.models.Product
import com.example.pmordo.presentation.ui.activity_main.MainActivity
import com.example.pmordo.presentation.ui.product.ProductAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.androidx.viewmodel.ext.android.viewModel
import retrofit2.Call
import retrofit2.Response

class HomeFragment :
    BaseFragment<FragmentHomeBinding, HomeViewModel>(FragmentHomeBinding::inflate) {
    override val viewModel: HomeViewModel by viewModel()

    private lateinit var searchView: SearchView
    private lateinit var productAdapter: ProductAdapter
    private lateinit var originalProducts: List<Product>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as MainActivity).setDrawerVisibility(true)
        showBottomNavigationView()

        val recyclerView = view.findViewById<RecyclerView>(R.id.productRecyclerView)
        val imageSlider = view.findViewById<ImageSlider>(R.id.image_slider)

        productAdapter = ProductAdapter(mutableListOf())
        val gridLayoutManager = GridLayoutManager(requireContext(), 2)
        recyclerView.layoutManager = gridLayoutManager
        recyclerView.adapter = productAdapter

        // Set up the search view
        searchView = view.findViewById(R.id.searchView)
        setupSearchView(searchView)

        // Load products from the API
        loadProducts()

        // Set up image slider
        val imageList = ArrayList<SlideModel>()
        imageList.add(SlideModel("https://kuhnoteka.ru/wp-content/uploads/2017/10/0482.jpg", ""))
        imageList.add(SlideModel("https://ekbkupe.ru/images/stories/virtuemart/product/2022-03-05_11-24-055.jpg", ""))
        imageList.add(SlideModel("https://www.cucine.ru/upload/resize_cache/iblock/214/1200_878_16b651e4f962b08687c210ba0fcd27736/miton-cucine-limha-eurolux-1.jpg", ""))
        imageList.add(SlideModel("https://dizainexpert.ru/wp-content/uploads/2020/07/kuhnya-haj-tek.jpg", ""))
        imageSlider.setImageList(imageList, ScaleTypes.FIT)

        // Handle item click
        productAdapter.setOnItemClickListener { product ->
            val action = HomeFragmentDirections.actionHomeFragmentToItemViewFragment(product)
            findNavController().navigate(action)
        }
    }

    private fun setupSearchView(searchView: SearchView) {
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filterProducts(newText.orEmpty())
                return true
            }
        })
    }

    private fun filterProducts(query: String) {
        val filteredList = originalProducts.filter { product ->
            product.name.contains(query, ignoreCase = true)
        }
        productAdapter.updateProductList(filteredList as MutableList<Product>)
    }

    private fun loadProducts() {
        val serviceGenerator = ServiceGenerator.buildService(ProductApi::class.java)
        val call = serviceGenerator.getProducts()

        call.enqueue(object : retrofit2.Callback<MutableList<Product>> {
            override fun onResponse(
                call: Call<MutableList<Product>>,
                response: Response<MutableList<Product>>
            ) {
                if (response.isSuccessful) {
                    val products = response.body() ?: emptyList()
                    originalProducts = products
                    productAdapter.updateProductList(products as MutableList<Product>)
                }
            }

            override fun onFailure(call: Call<MutableList<Product>>, t: Throwable) {
                t.printStackTrace()
                Log.e("error", t.message.toString())
            }
        })
    }
}
