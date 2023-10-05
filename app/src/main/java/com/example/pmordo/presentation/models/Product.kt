package com.example.pmordo.presentation.models
import android.os.Parcel
import android.os.Parcelable

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
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readInt(),
        parcel.readInt(),
        parcel.createTypedArrayList(ProductImage.CREATOR) ?: emptyList(),
        parcel.createTypedArrayList(ProductSpecification.CREATOR) ?: emptyList(),
        parcel.readInt(),
        parcel.readInt()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeString(brand)
        parcel.writeString(country_of_origin)
        parcel.writeString(description)
        parcel.writeInt(price)
        parcel.writeInt(quantity)
        parcel.writeTypedList(images)
        parcel.writeTypedList(specifications)
        parcel.writeInt(category)
        parcel.writeInt(store)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Product> {
        override fun createFromParcel(parcel: Parcel): Product {
            return Product(parcel)
        }

        override fun newArray(size: Int): Array<Product?> {
            return arrayOfNulls(size)
        }
    }
}


data class ProductImage(
    val id: Int,
    val image: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString() ?: ""
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(image)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ProductImage> {
        override fun createFromParcel(parcel: Parcel): ProductImage {
            return ProductImage(parcel)
        }

        override fun newArray(size: Int): Array<ProductImage?> {
            return arrayOfNulls(size)
        }
    }
}

data class ProductSpecification(
    val id: Int,
    val name: String,
    val value: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString() ?: "",
        parcel.readString() ?: ""
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeString(value)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ProductSpecification> {
        override fun createFromParcel(parcel: Parcel): ProductSpecification {
            return ProductSpecification(parcel)
        }

        override fun newArray(size: Int): Array<ProductSpecification?> {
            return arrayOfNulls(size)
        }
    }
}


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

