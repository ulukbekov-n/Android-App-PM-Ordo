package com.example.pmordo.presentation.ui.product

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pmordo.R
import com.example.pmordo.databinding.ItemProductBinding
import com.example.pmordo.presentation.models.Product

class ProductAdapter(private var productList: List<Product>) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {
    private var onItemClick: ((Product) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemProductBinding.inflate(inflater, parent, false)
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = productList[position]
        holder.bindProduct(product)
        holder.itemView.setOnClickListener {
            onItemClick?.invoke(product)
        }
    }

    override fun getItemCount(): Int {
        return productList.size
    }
    private var itemClickListener: ((Product) -> Unit)? = null

    fun setOnItemClickListener(listener: (Product) -> Unit) {
        itemClickListener = listener
    }

    inner class ProductViewHolder(private val binding: ItemProductBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bindProduct(product: Product) {
            binding.productName.text = product.name
            binding.productPrice.text = product.price.toString()

            if (product.images.isNotEmpty()) {
                val firstImage = product.images[0]
                Glide.with(binding.productImageView.context)
                    .load(firstImage)
                    .placeholder(R.drawable.search_icon)

                    .into(binding.productImageView)
            }
            binding.root.setOnClickListener {
                itemClickListener?.invoke(product)
            }

        }
    }

    fun updateProductList(newList: List<Product>) {
        productList = newList
        notifyDataSetChanged()
    }
}
