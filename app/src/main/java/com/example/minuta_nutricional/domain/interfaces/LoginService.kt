package com.example.minuta_nutricional.domain.interfaces

sealed class LoginValidationResult {
    object Valid : LoginValidationResult()

    data class Invalid(val errorMessage: String) : LoginValidationResult()
}

interface LoginService {
    fun validarCredenciales(usuario: String,password: String) : LoginValidationResult
}