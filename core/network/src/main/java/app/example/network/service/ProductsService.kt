package app.example.network.service

import app.example.network.model.response.Product
import retrofit2.http.GET

interface ProductsService {
    @GET("products")
    suspend fun getProducts(): List<Product>
}