package app.example.domain.usecase

import app.example.domain.model.ProductList
import app.example.domain.repository.ProductsRepository
import app.example.domain.repository.Result
import javax.inject.Inject

class FetchProductsUseCase @Inject constructor(
    private val productsRepository: ProductsRepository
) {
    suspend operator fun invoke(): Result<ProductList> {
        return productsRepository.fetchProducts()
    }
}