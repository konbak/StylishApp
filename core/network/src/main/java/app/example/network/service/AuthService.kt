package app.example.network.service

import app.example.network.model.requests.LoginRequest
import app.example.network.model.response.LoginResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("auth/login")
    suspend fun login(@Body request: LoginRequest): LoginResponse
}