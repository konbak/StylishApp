package app.example.domain.repository

import app.example.domain.model.LoginData


sealed class LoginResult {
    data class Success(val data: LoginData) : LoginResult()
    data class Error(val message: String) : LoginResult()
}