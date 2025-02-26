package app.example.data.repository

import app.example.domain.repository.AuthRepository
import app.example.network.model.requests.LoginRequest
import app.example.network.service.AuthService
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authService: AuthService
) : AuthRepository {

    override suspend fun login(username: String, password: String): String {
        val loginRequest = LoginRequest(username, password)
        return authService.login(loginRequest).token
    }
}