package app.example.domain.repository

interface AuthRepository {
    suspend fun login(username: String, password: String): LoginResult
}