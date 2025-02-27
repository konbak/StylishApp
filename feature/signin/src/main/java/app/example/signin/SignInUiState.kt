package app.example.signin

data class SignInUiState(
    val username: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val isSignedIn: Boolean = false,
    val errorMessage: String? = null,
)
