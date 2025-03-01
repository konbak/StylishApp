package app.example.data.repository

import app.example.data.mapper.ProductMapper
import app.example.domain.model.ProductList
import app.example.domain.repository.ProductsRepository
import app.example.domain.repository.Result
import app.example.network.service.ProductsService
import javax.inject.Inject

class ProductsRepositoryImpl @Inject constructor(
    private val productsService: ProductsService,
    private val productMapper: ProductMapper,
) : ProductsRepository {
    override suspend fun fetchProducts(): Result<ProductList> {

        return try {
            val products = productsService.getProducts()
            val productList = productMapper.mapToDomainList(products)
            Result.Success(productList)
        } catch (e: Exception) {
            Result.Error("Fetching products failed: ${e.message}")
        }
    }
}