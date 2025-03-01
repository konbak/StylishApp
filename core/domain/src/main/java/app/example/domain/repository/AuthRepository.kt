package app.example.domain.repository

import app.example.domain.model.LoginData

interface AuthRepository {
    suspend fun login(username: String, password: String): Result<LoginData>
}