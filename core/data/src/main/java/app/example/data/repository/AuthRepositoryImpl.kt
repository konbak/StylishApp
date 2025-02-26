package app.example.data.repository

import app.example.data.mapper.LoginResponseMapper
import app.example.domain.repository.AuthRepository
import app.example.domain.repository.LoginResult
import app.example.network.model.requests.LoginRequest
import app.example.network.service.AuthService
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authService: AuthService
) : AuthRepository {

    override suspend fun login(username: String, password: String): LoginResult {
        val loginRequest = LoginRequest(username, password)

        return try {
            val response = authService.login(loginRequest)
            val loginData = LoginResponseMapper().mapToDomain(response)
            LoginResult.Success(loginData)
        } catch (e: Exception) {
            LoginResult.Error("Login failed: ${e.message}")
        }
    }
}