package app.example.data.datastore

import app.example.domain.repository.TokenRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TokenRepositoryImpl @Inject constructor(
    private val preferencesManager: PreferencesManager,
) : TokenRepository {

    override val tokenFlow: Flow<String?> = preferencesManager.tokenFlow

    override suspend fun saveToken(token: String) {
        preferencesManager.saveToken(token)
    }

    override suspend fun clearToken() {
        preferencesManager.clearToken()
    }
}