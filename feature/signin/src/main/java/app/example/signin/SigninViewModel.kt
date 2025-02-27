package app.example.signin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.example.domain.repository.LoginResult
import app.example.domain.repository.TokenRepository
import app.example.domain.usecase.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SigninViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val tokenRepository: TokenRepository,
) : ViewModel() {
    private val _uiState = MutableStateFlow(SignInUiState())
    val uiState: StateFlow<SignInUiState> = _uiState

    fun updateCredentials(username: String, password: String) {
        _uiState.update { currentState ->
            currentState.copy(
                username = username,
                password = password,
            )
        }
    }

    fun signIn() = viewModelScope.launch {
        updateUiLoadingState(isLoading = true)

        val result = loginUseCase(
            _uiState.value.username,
            _uiState.value.password,
        )

        when (result) {
            is LoginResult.Success -> {
                tokenRepository.saveToken(result.data.token)
                updateUiSignedInState()
            }

            is LoginResult.Error -> {
                updateUiLoadingState(
                    isLoading = false,
                    errorMessage = result.message,
                )
            }
        }
    }

    private fun updateUiLoadingState(
        isLoading: Boolean,
        errorMessage: String? = null,
    ) {
        _uiState.update { currentState ->
            currentState.copy(
                isLoading = isLoading,
                errorMessage = errorMessage,
            )
        }
    }

    private fun updateUiSignedInState() {
        _uiState.update { currentState ->
            currentState.copy(isSignedIn = true)
        }
    }
}