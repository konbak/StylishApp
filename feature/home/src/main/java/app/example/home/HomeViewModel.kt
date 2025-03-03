package app.example.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.example.domain.model.ProductDomain
import app.example.domain.repository.Result
import app.example.domain.usecase.FetchProductsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val fetchProductsUseCase: FetchProductsUseCase,
) : ViewModel() {
    private val _products = MutableStateFlow<List<ProductDomain>>(emptyList())
    val products: StateFlow<List<ProductDomain>> = _products

    init {
        fetchProducts()
    }

    private fun fetchProducts() = viewModelScope.launch {
        when (val result = fetchProductsUseCase()) {
            is Result.Success -> _products.value = result.data.products
            is Result.Error -> println("Error fetching products: ${result.message}")
        }
    }
}