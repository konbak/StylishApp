package app.example.data.mapper

import app.example.domain.model.LoginData
import app.example.network.model.response.LoginResponse

class LoginResponseMapper {
    fun mapToDomain(loginResponse: LoginResponse): LoginData {
        return LoginData(token = loginResponse.token)
    }
}