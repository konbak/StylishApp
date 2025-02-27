package app.example.domain.repository

import kotlinx.coroutines.flow.Flow

interface TokenRepository {
    val tokenFlow: Flow<String?>
    suspend fun saveToken(token: String)
    suspend fun clearToken()
}