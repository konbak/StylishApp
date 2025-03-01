package app.example.data.mapper

import app.example.domain.model.ProductDomain
import app.example.domain.model.ProductList
import app.example.network.model.response.Product

class ProductMapper(
    private val ratingMapper: RatingMapper
) {
    fun mapToDomain(product: Product): ProductDomain {
        return ProductDomain(
            id = product.id,
            title = product.title,
            price = product.price,
            description = product.description,
            category = product.category,
            image = product.image,
            rating = ratingMapper.mapToDomain(product.rating)
        )
    }

    fun mapToDomainList(products: List<Product>): ProductList {
        return ProductList(products.map { mapToDomain(it) })
    }
}
