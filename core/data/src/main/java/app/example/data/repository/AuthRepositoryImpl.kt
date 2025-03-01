package app.example.data.repository

import app.example.data.mapper.LoginResponseMapper
import app.example.domain.model.LoginData
import app.example.domain.repository.Result
import app.example.domain.repository.AuthRepository
import app.example.network.model.requests.LoginRequest
import app.example.network.service.AuthService
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authService: AuthService
) : AuthRepository {

    override suspend fun login(username: String, password: String): Result<LoginData> {
        val loginRequest = LoginRequest(username, password)

        return try {
            val response = authService.login(loginRequest)
            val loginData = LoginResponseMapper().mapToDomain(response)
            Result.Success(loginData)
        } catch (e: Exception) {
            Result.Error("Login failed: ${e.message}")
        }
    }
}