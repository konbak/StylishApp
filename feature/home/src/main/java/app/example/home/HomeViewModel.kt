package app.example.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.example.domain.model.ProductList
import app.example.domain.repository.Result
import app.example.domain.usecase.FetchProductsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val fetchProductsUseCase: FetchProductsUseCase,
) : ViewModel() {

    init {
        fetchProducts()
    }

    private fun fetchProducts() = viewModelScope.launch {
        val result: Result<ProductList> = fetchProductsUseCase()
        println(result)
    }
}