package app.example.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.example.domain.repository.TokenRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashScreenViewModel @Inject constructor(
    private val tokenRepository: TokenRepository,
) : ViewModel() {
    private val _token = MutableStateFlow<String?>(null)
    val token: StateFlow<String?> = _token

    init {
       getToken()
    }

    private fun getToken() = viewModelScope.launch {
        tokenRepository.tokenFlow.collect { token ->
            _token.value = token
        }
    }
}