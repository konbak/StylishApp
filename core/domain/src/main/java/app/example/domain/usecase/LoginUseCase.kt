package app.example.domain.usecase

import app.example.domain.repository.AuthRepository
import javax.inject.Inject


class LoginUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(username: String, password: String): String {
        return authRepository.login(username, password)
    }
}