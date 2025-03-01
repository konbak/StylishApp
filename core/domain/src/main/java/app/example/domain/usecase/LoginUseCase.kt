package app.example.domain.usecase

import app.example.domain.model.LoginData
import app.example.domain.repository.AuthRepository
import app.example.domain.repository.Result
import javax.inject.Inject


class LoginUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(username: String, password: String): Result<LoginData> {
        return authRepository.login(username, password)
    }
}
