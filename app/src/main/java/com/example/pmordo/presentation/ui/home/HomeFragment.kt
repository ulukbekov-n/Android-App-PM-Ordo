package com.example.pmordo.presentation.ui.home
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment :
    BaseFragment<FragmentHomeBinding, HomeViewModel>(FragmentHomeBinding::inflate) {
    override val viewModel: HomeViewModel by viewModel()

    private lateinit var searchView: SearchView
    private lateinit var productAdapter: ProductAdapter
    private val allProducts: List<Product> = createDummyProducts()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as MainActivity).setDrawerVisibility(true)
        showBottomNavigationView()
        val imageSlider = view.findViewById<ImageSlider>(R.id.image_slider)
        val imageList = ArrayList<SlideModel>()
        imageList.add(SlideModel("https://kuhnoteka.ru/wp-content/uploads/2017/10/0482.jpg", ""))
        imageList.add(SlideModel("https://ekbkupe.ru/images/stories/virtuemart/product/2022-03-05_11-24-055.jpg", ""))
        imageList.add(SlideModel("https://www.cucine.ru/upload/resize_cache/iblock/214/1200_878_16b651e4f962b08687c210ba0fcd27736/miton-cucine-limha-eurolux-1.jpg", ""))
        imageList.add(SlideModel("https://dizainexpert.ru/wp-content/uploads/2020/07/kuhnya-haj-tek.jpg", ""))
        imageSlider.setImageList(imageList, ScaleTypes.FIT)

        productAdapter = ProductAdapter(emptyList())

        val gridLayoutManager = GridLayoutManager(requireContext(), 2)
        binding().productRecyclerView.layoutManager = gridLayoutManager
        binding().productRecyclerView.adapter = productAdapter

        binding().becomeSeller.setOnClickListener { findNavController().navigate(R.id.action_homeFragment_to_sellerRegisterFragment2) }

        searchView = view.findViewById(R.id.searchView)
        setupSearchView(searchView)
        loadProducts(allProducts)
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
        val filteredList = allProducts.filter { product ->
            product.name.contains(query, ignoreCase = true)
        }
        productAdapter.updateProductList(filteredList)
    }

    private fun loadProducts(allProducts: List<Product>) {
        lifecycleScope.launch(Dispatchers.IO) {
            try {
                // Replace this with your actual API call or use createDummyProducts for sample data
                val products = createDummyProducts()
                withContext(Dispatchers.Main) {
                    productAdapter.updateProductList(products)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    // Create sample products for demonstration
    private fun createDummyProducts(): List<Product> {
        val products = mutableListOf<Product>()

        val product1 = Product(
            id = 1,
            name = "Product 1",
            brand = "Brand 1",
            country_of_origin = "Origin 1",
            description = "Description of Product 1",
            price = 10,
            quantity = 100,
            images = listOf(ProductImage(1, "image1.jpg")),
            specifications = listOf(ProductSpecification(1, "Spec 1", "Value 1")),
            category = 1,
            store = 1
        )

        val product2 = Product(
            id = 2,
            name = "Product 2",
            brand = "Brand 2",
            country_of_origin = "Origin 2",
            description = "Description of Product 2",
            price = 20,
            quantity = 200,
            images = listOf(ProductImage(2, "image2.jpg")),
            specifications = listOf(ProductSpecification(2, "Spec 2", "Value 2")),
            category = 2,
            store = 2
        )

        val product3 = Product(
            id = 3,
            name = "Product 3",
            brand = "Brand 3",
            country_of_origin = "Origin 3",
            description = "Description of Product 3",
            price = 30,
            quantity = 300,
            images = listOf(ProductImage(3, "image3.jpg")),
            specifications = listOf(ProductSpecification(3, "Spec 3", "Value 3")),
            category = 3,
            store = 3
        )

        products.add(product1)
        products.add(product2)
        products.add(product3)

        return products
    }

}
