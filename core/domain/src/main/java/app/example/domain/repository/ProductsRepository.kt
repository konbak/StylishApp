package app.example.domain.repository

import app.example.domain.model.ProductList

interface ProductsRepository {
    suspend fun fetchProducts(): Result<ProductList>
}